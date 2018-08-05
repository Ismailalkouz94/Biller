import {
  Component,
  OnInit,
  ViewEncapsulation,
  ViewContainerRef, Injectable
} from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { FileUploader } from "ng2-file-upload/ng2-file-upload";
import { Http, Headers, RequestOptions } from "@angular/http";
import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { appConfig } from "../../../app.config";
import { NotificationsService } from "../../../notifications/notifications.service";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import "rxjs/add/operator/map";
import { Router } from "@angular/router";
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Config } from 'ngx-easy-table/src/app/ngx-easy-table/model/config';
import { ConfirmationDialog } from '../../../confirm-dialog/confirmation-dialog';
import { MdDialog, MdDialogRef } from '@angular/material';
import { SharedModule } from '../../../SharedModule';
@Component({
  selector: "ms-invoice",
  templateUrl: "./invoiceDetails.component.html",
  styleUrls: ["../../../../assets/scss/myStyle.scss"],
  
  animations: [
    trigger('dialog', [
      transition('void => *', [
        style({ transform: 'scale3d(.3, .3, .3)' }),
        animate(100)
      ]),
      transition('* => void', [
        animate(100, style({ transform: 'scale3d(.0, .0, .0)' }))
      ])
    ])],
})

export class InvoiceDetailsComponent implements OnInit {
  downloadFileURL: string;
  uploader: FileUploader = new FileUploader({url: appConfig.apiUrl + 'importFiles/upload'});
  hasBaseDropZoneOver = false;
  hasAnotherDropZoneOver = false;
     
  fileOverBase(e: any): void {
      this.hasBaseDropZoneOver = e;
      this.fileToUpload=e;
  }

  fileOverAnother(e: any): void {
      this.hasAnotherDropZoneOver = e;
  }
  dialogRef: MdDialogRef<ConfirmationDialog>;
  favPartyList: any;
  public invoiceList;
  public data;
  updateList;
  invoiceTypelist;
  invoiceStatuslist;
  public filterQuery = "";
  public rowsOnPage = 5;
  public sortBy = "invoiceDate";
  public sortOrder = "asc";
  invoiceDate;
  params;

  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public http: Http,
    public router: Router,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public confirmDialog: MdDialog

  ) {
    this.toastr.setRootViewContainerRef(vcr);
    this.configurationAdvanced = ConfigurationAdvancedService.config;
  }

  configurationAdvanced;
  columns = [
    { key: 'invoiceId', title: 'رقم الفاتورة' },
    { key: 'referenceNumber', title: 'رقم المرجع' },
    { key: 'description', title: 'الوصف' },
    { key: 'status', title: 'الحالة' },
    { key: 'invoiceDate', title: 'التاريخ' },
    { key: 'dueDate', title: 'تاريخ الاستحقاق' },
    { key: 'paidDate', title: 'تاريخ الدفع' },
    { key: ' total', title: ' المجموع' },
    { key: ' ', title: ' ' },
  ];

  getInvoiceData() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");

    let options = new RequestOptions({
      headers: headers,
      params: {
        fromPartyId: localStorage.getItem("partyId"),
        invoiceTypeId: "INVOICE_DETAILS"
      }
    });
    this.http
      .get(appConfig.apiUrl + "invoice/findByFromPartyAndInvoiceType", options)
      .map(res => res.json())
      .subscribe(invoiceList => {
        this.invoiceList = invoiceList.data;
      });
  }
  dialog = false;
  fileToUpload: File = null;
  success:boolean;
  ngOnInit() {

    this.downloadFileURL=appConfig.apiUrl + 'importFiles/downloadTempl?partyId='+sessionStorage.getItem("partyId"),

    localStorage.setItem("isReceived", "false");
    this.form = this.fb.group({
      toName: [],
      // invoiceTypeId: [null, Validators.compose([Validators.required])],
      description: [],
      statusId: [],
      toPartyId: [],
      orderId: [],
      // email: [null, Validators.compose([CustomValidators.email])],
      // referenceNumber: [null],
      // mobileNumber: [null, Validators.pattern("[0-9]*")],
      paidNumber: [],
      invoiceDate: [null, Validators.compose([CustomValidators.date])],
      dueDate: [null, Validators.compose([CustomValidators.date])],
      invoiceId: [],
      paidDate: [null, Validators.compose([CustomValidators.date])]
    });
    this.getInvoiceData();
    this.getInvoiceType();
    this.getInvoiceStatus();
    this.getSentTo();
  }

  openConfirmationDialog(invoiceId) {
    this.dialogRef = this.confirmDialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to delete?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.delete(invoiceId);
      }
      this.dialogRef = null;
    });
  }

  delete(invoiceId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { invoiceId: invoiceId }
    });

    this.http.delete(appConfig.apiUrl + "invoiceDetails/deleteInvoiceAndDetails", options).map(res => res.json()).subscribe(data => {
      if (!data.isError) {
        this.showSuccess(data.messageAR);
        this.getInvoiceData();
      } else {
        this.showError(data.messageEN);
      }
    },
      error => {
        this.showError("حدث خطأ ما ");
      }
    );
  }

  hiddenBtn = "false";
  update(invoiceId) {
    localStorage.setItem("invoiceId", invoiceId);
    this.router.navigate(["/invoice/update-invoice"]);
  }
  param;

  changeToCancel(invoiceId) {
    this.dialogRef = this.confirmDialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Cancel?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let url = appConfig.apiUrl + 'invoice/changeStatus_Custom';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let params = new URLSearchParams();
        params.append('invoiceId', invoiceId);
        params.append('status', 'INVOICE_CANCELLED');
        let body = params.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
          if (data.isError == false) {
            this.getInvoiceData();
            this.showSuccess(data.messageAR);
          } else {
            this.showError(data.data);
          }
        }, error => {
          this.showError("ERROR");
        })
      }
      this.dialogRef = null;
    });
  }

  getInvoiceType() {
    this.http
      .get(
      appConfig.apiUrl +
      "globalItem/findByCriteria?globalTypeId=INVOICE_TYPE_ID"
      )
      .map(res => res.json())
      .subscribe(invoiceTypelist => {
        this.invoiceTypelist = invoiceTypelist;
        return this.invoiceTypelist;
      });
  }

  getInvoiceStatus() {
    this.http
      .get(
      appConfig.apiUrl +
      "globalItem/findByCriteria?globalTypeId=INVOICE_STATUS"
      )
      .map(res => res.json())
      .subscribe(invoiceStatuslist => {
        this.invoiceStatuslist = invoiceStatuslist;
      });
  }

  public toInt(num: string) {
    return +num;
  }

  public sortByWordLength = (a: any) => {
    return a.invoiceId.length;
  };

  findByCriteria(formData) {
    // alert(formData.invoiceId);

    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    console.log(formData);
    // if (formData.invoiceDate != null) {

    //   this.invoiceDate = new Date(formData.invoiceDate);
    // }
    this.invoiceDate = formData.invoiceDate;
    this.params = {
      fromPartyId: localStorage.getItem("partyId"),
      invoiceId: formData.invoiceId,
      description: formData.description,
      toName: formData.toName,
      invoiceDate: this.invoiceDate,
      dueDate: formData.dueDate,
      invoiceTypeId: "INVOICE_DETAILS",
      toPartyId: formData.toPartyId,
      paidDate: formData.paidDate,
      orderId: formData.orderId,
      statusId: formData.statusId,
      paidNumber: formData.paidNumber
    };
    for (var key in this.params) {
      if (this.params[key] === null) {
        delete this.params[key];
      }
    }
    let options = new RequestOptions({
      headers: headers,
      params: this.params
    });

    this.http
      .get(appConfig.apiUrl + "invoice/findByCriteria", options)
      .map(res => res.json())
      .subscribe(invoiceList => {
        this.invoiceList = invoiceList;
      });
  }

  getFavParties() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { partyId: localStorage.getItem("partyId") }
    });
    this.http
      .get(appConfig.apiUrl + "partyGroupFav/listAllFavByParty", options)
      .map(res => res.json())
      .subscribe(data => {
        this.favPartyList = data;
        return data;
      });
  }

  showTheBill(layout) {
    let popupWin = window.open('تقرير تفصيلي', '_blank');


    popupWin.document.open();
    popupWin.document.write(layout);
    popupWin.document.close();
  }

  invoiceDetailsReport(invoiceId) {


    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");
    this.param = {
      "partyId": localStorage.getItem("partyId"),
      "templateId": "INVOICE_DETAILS",
      "invoiceId": invoiceId
    }

    var body = JSON.stringify(this.param);
    this.http
      .post(link, body, { headers })
      .map(res => res.json())
      .subscribe(
      invoiceList => {
        this.showTheBill(invoiceList.data);
        // this.hiddenBtn="true";
        // this.getInvoiceData();
      },
      error => {

        this.showError("خطأ");
        // this.notification.showError("error occured");
      }
      );

  }

  print(invoiceId) {
    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");
    this.param = {
      "partyId": localStorage.getItem("partyId"),
      "templateId": "CUSTOM",
      "invoiceId": invoiceId
    }
    var body = JSON.stringify(this.param);
    this.http
      .post(link, body, { headers })
      .map(res => res.json())
      .subscribe(

      invoiceList => {
        this.showTheBill(invoiceList.data);
        // this.hiddenBtn="true";
        // this.getInvoiceData();
      },
      error => {
        this.showError("خطأ");
        // this.notification.showError("error occured");
      }
      );


  }
  getTotal(invoiceDetails) {
    let total;
    for (let item in invoiceDetails) {
      if(invoiceDetails[item].dataFieldType.dataFieldTypeKey=="TOTAL_AMOUNT"){
        total = invoiceDetails[item].dataFieldTypeValue;
      }
    }
    return total;
  }

  public show: boolean = false;
  public buttonName: any = "بحث متقدم";
  public arraw: any = "arrow_drop_down";

  toggle() {
    this.show = !this.show;

    // CHANGE THE NAME OF THE BUTTON.
    if (this.show) {
      this.buttonName = "اخفاء";
      this.arraw = "arrow_drop_up";
    } else {
      this.buttonName = "بحث متقدم";
      this.arraw = "arrow_drop_down";
    }
  }
  sentToList;
  getSentTo() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { fromPartyId: localStorage.getItem("partyId") }
    });
    this.http
      .get(appConfig.apiUrl + "invoice/findBySentTo", options)
      .map(res => res.json())
      .subscribe(data => {
        this.sentToList = data.data;
      });
  }

  showSuccess(message) {
    this.toastr.success(message, "");
  }
  showError(message) {
    this.toastr.error(message, "خطأ!");
  }
  showWarning(message) {
    this.toastr.warning(message, "الرجاء الإنتباه!");
  }


  // .... for upload File

  openUploadDialog() {
    this.dialog = true;
  }

  closeUploadDialog(event : any) {
    this.dialog = false;
    let element = document.getElementById('butRem');
    element.click();
    
  }

  onCloseHandled() {
    (<any>this).display = 'none';
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  postFile() {
    const endpoint = 'importFiles/upload';
    const formData: FormData = new FormData();
    formData.append('file', this.fileToUpload, this.fileToUpload.name);
    formData.append('partyId', localStorage.getItem("partyId"));
    formData.append('userLoginId', localStorage.getItem("userLoginId"));
    this.http.post(appConfig.apiUrl + endpoint, formData).map(res => res.json()).subscribe((data) => {
      this.getInvoiceData();
      if (data.isError == false) {
        this.showSuccess(data.messageAR);
        this.success=true;
        this.closeUploadDialog(event);
      } else {
        this.success=false;
        this.showError(data.messageAR + " -- " + data.data);
      }
    }, error => {
      this.success=false;
      this.showError("ERROR");
    })
  }

}


@Injectable()
export class ConfigurationAdvancedService {
  public static config: Config = {
    searchEnabled: true,
    headerEnabled: true,
    orderEnabled: true,
    globalSearchEnabled: false,
    paginationEnabled: true,
    exportEnabled: false,
    clickEvent: false,
    selectRow: true,
    selectCol: false,
    selectCell: false,
    rows: 10,
    additionalActions: false,
    serverPagination: false,
    isLoading: false,
    detailsTemplate: false,
    groupRows: false,
    paginationRangeEnabled: true,
    collapseAllRows: false,
    checkboxes: false
  };
}
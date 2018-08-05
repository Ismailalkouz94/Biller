import {
  Component,
  OnInit,
  Injectable,
  ViewEncapsulation,
  ViewContainerRef
} from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { FileUploader } from "ng2-file-upload/ng2-file-upload";
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { fadeInAnimation } from "../../../../core/route-animation/route.animation";
import { appConfig } from "../../../../app.config";
import { NotificationsService } from "../../../../notifications/notifications.service";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import "rxjs/add/operator/map";
import { Router } from "@angular/router";
import { trigger, state, style, animate, transition } from '@angular/animations';
import { MdDialog, MdDialogRef } from '@angular/material';
import { ConfirmationDialog } from '../../../../confirm-dialog/confirmation-dialog';
import { ConfigurationAdvancedService } from "../../../../shared/service/configuration.service";

// import { data } from './../../../../../assets/data/data';
@Component({
  selector: "ms-invoice",
  templateUrl: "./invoiceSent.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"],

})
export class InvoiceSentComponent implements OnInit {
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

  dialogRef: MdDialogRef<ConfirmationDialog>;
  public form: FormGroup;
  index =0;
  configurationAdvanced;
  columns = [
  
    { key: 'invoiceId', title: 'رقم الفاتورة' },
    { key: 'referenceNumber', title: 'رقم الإشتراك' },
    { key: 'description', title: 'الوصف' },
    { key: 'status', title: 'الحالة' },
    { key: 'invoiceDate', title: 'التاريخ' },
    { key: 'dueDate', title: 'تاريخ الاستحقاق' },
    { key: 'paidDate', title: 'تاريخ الدفع' },
    { key: 'toParty', title: 'طرف معرف' },
    { key: 'toName', title: 'العميل الزبون' },
    { key: 'orderId', title: ' الطلب' },
    { key: ' total', title: ' مجموع السلع' },
    { key: ' ', title: ' ' },
    { key: ' ', title: ' ' },
  ];

  data2 = [];
  constructor(
    private fb: FormBuilder,
    public http: Http,
    public router: Router,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public dialog: MdDialog

  ) {
    this.toastr.setRootViewContainerRef(vcr);
	this.configurationAdvanced = ConfigurationAdvancedService.config;
    
  }


  getInvoiceData() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");

    let options = new RequestOptions({
      headers: headers,
      params: {
        fromPartyId: localStorage.getItem("partyId"),
        invoiceTypeId: "INVOICE_ITEM"
      }
    });
    this.http
      .get(appConfig.apiUrl + "invoice/findByFromPartyAndInvoiceType", options)
      .map(res => res.json())
      .subscribe(invoiceList => {
        this.invoiceList = invoiceList.data;
      });

  }
  // dialog=false;


  ngOnInit() {
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
      referenceNumber: [],
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
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "هل انت متأكد من عملية الحذف ؟"
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
      params: { id: invoiceId }
    });

    this.http
      .delete(appConfig.apiUrl + "invoice/delete", options)
      .map(res => res.json())
      .subscribe(
        data => {
          if (!data.isError) {
            this.showSuccess(data.messageAR);
            this.getInvoiceData();
          } else {
            this.showError("حدث خطأ ما ");
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
  sentInvoice(invoice, amount,) {
    if (invoice.status.globalItemId === "INVOICE_UNPAID") {
      this.showWarning(" لا تسطيع ارسال الفاتورة تم ارسالها من قبل ");
      return "";
    }
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "هل انت متأكد من عملية الإرسال ؟"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        var link = appConfig.apiUrl + "invoice/changeStatus";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");

  if(amount==0){
    this.showWarning("لم تقم باضافة سلع للفاتورة");
    return 0;

   }

   

    this.param = {

      status: "INVOICE_UNPAID",
      fromPartyId :invoice.fromParty.partyId,
      fromPartyName: invoice.fromParty.partyGroup.partyGroupName,
      toPartyId:invoice.toParty.partyId,
      amount: amount,
      invoiceId: invoice.invoiceId,
      templateId:"INVOICE_DETAILS",
      title:invoice.description
    };

    var body = JSON.stringify(this.param);
    this.http
      .post(link, body, { headers })
      .map(res => res.json())
      .subscribe(


        invoiceList => {
          this.showSuccess(invoiceList.messageAR);
this.hiddenBtn="true";
this.getInvoiceData();
        },
        error => {
         
          this.showError("خطأ");
          // this.notification.showError("error occured");
        }
      ); 
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
      invoiceTypeId: "INVOICE_ITEM",
      dueDate: formData.dueDate,
      toPartyId: formData.toPartyId,
      paidDate: formData.paidDate,
      orderId: formData.orderId,
      statusId: formData.statusId,
      paidNumber: formData.referenceNumber
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


   showTheBill(layout){
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
	"partyId":localStorage.getItem("partyId"),
	"templateId":"INVOICE_DETAILS",
	"invoiceId":invoiceId	
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

  invoiceTotalReport(invoiceId) {


    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");

     this.param = {
      "partyId":localStorage.getItem("partyId"),
      "templateId":"INVOICE_TOTAL",
      "invoiceId":invoiceId  
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
  totalPrice(invoiceItem) {
    let total = 0;
    for (let item in invoiceItem) {
      total += invoiceItem[item].unitPrice * invoiceItem[item].quantity;
    }
    console.log(total);
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
    this.toastr.success(message, "success");
  }
  showError(message) {
    this.toastr.error(message, "خطأ!");
  }
  showWarning(message) {
    this.toastr.warning(message, "الرجاء الإنتباه!");
  }


}

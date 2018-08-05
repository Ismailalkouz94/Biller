import {
  Component,
  OnInit,
  ViewEncapsulation,
  ViewContainerRef
} from "@angular/core";
import {
  FormGroup,
  Validators,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { FileUploader } from "ng2-file-upload/ng2-file-upload";
import { Http, Headers, RequestOptions } from "@angular/http";
import { fadeInAnimation } from "../../../../core/route-animation/route.animation";
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ActivatedRoute, Router } from "@angular/router";
import { appConfig } from "../../../../app.config";
import { NotificationsService } from "../../../../notifications/notifications.service";
import { SelectItem } from '../../../../shared/guards/tree/selectitem';
import { OnChanges } from '@angular/core';

@Component({
  selector: "ms-add-invoice",
  templateUrl: "./add-invoice.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"]
})
export class AddInvoiceComponent implements OnInit {
  btnLabel: String;
  showSelected: boolean;
  disabel: boolean = true;
  invoiceList;
    favPartyList;
  updateInvoiceList;
  invoiceId;
  invoiceTypelist: Array<any> = [];
  res;
  isPartyToRadio:boolean;
  isPartialyRadio:boolean;
  public form: FormGroup;
  constructor(
    // private fb: FormBuilder,
    public http: Http,
    private router: Router,
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef
  ) // private notification: NotificationsService
  // public notification:Notifications
  {

    this.showSelected = false;
    this.btnLabel = "اضافة";
    this.toastr.setRootViewContainerRef(vcr);
     
  }
  partyId;
  ngOnInit() {
    // alert(this.fromParty);
    this.getInvoiceType();
    this.getFavParties();
 this.setFormValidation();

 this.form.get('userLoginId').setValue(localStorage.getItem("userLoginId"));
 this.form.get('fromPartyId').setValue(localStorage.getItem("partyId"));
 this.form.get('statusId').setValue("INVOICE_CREATED");
 this.form.get('isPartially').setValue("N");
 this.form.get('invoiceTypeId').setValue("INVOICE_ITEM");
  }

  add() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    if (this.btnLabel == "اضافة") {
      var link = appConfig.apiUrl + "invoice/add";

      var body = JSON.stringify(this.form.value);
      this.http
        .post(link, body, { headers })
        .map(res => res.json())
        .subscribe(
          invoiceList => {
            
            // this.notification.showSuccess(invoiceList.successMSG);
            this.showSelected = true;
            // this.router.navigate(['/transactions/update-invoice']);
            this.btnLabel = "تعديل";
           
            localStorage.setItem("invoiceId", invoiceList.data.invoiceId);
            this.invoiceId = invoiceList.data.invoiceId;
         
            this.showSuccess(invoiceList.messageAR);
            // this.router.navigate(['/transactions/update-invoice'],{ queryParams: { id: invoiceList.invoiceId } });
          },
          error => {
           this.showError("حدث خطأ ما في اضافة الفاتورة")

            // this.notification.showError("error occured");
          }
        );
    } else if (this.btnLabel == "تعديل") {
      console.log(this.invoiceId);
      var link = appConfig.apiUrl + "invoice/update";
      // this.updateInvoiceList = ;
      // var invoiceId = {"invoiceId":this.invoiceId};
      // this.updateInvoiceList.push({"invoiceId":this.invoiceId});
      // console.log(this.updateInvoiceList);
      this.invoiceId = this.invoiceId;
      var body = JSON.stringify(this.form.value);
      this.http
        .put(link, body, { headers })
        .map(res => res.json())
        .subscribe(
          invoiceList => {
            this.showSuccess(invoiceList.messageAR);
          },
          error => {
           
            this.showError("خطأ");
            // this.notification.showError("error occured");
          }
        );
    }
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
      });
  }

  setFormValidation() {
    this.form = this.fb.group({
      fromPartyId: [],
      toName: [],
      description:  [null,Validators.required],
      paidNumber :[],
      statusId: [],
      invoiceTypeId:[null,Validators.required],
      partyIdTo: [null,Validators.required],
      orderId: [null],
      email: [
        null
             ],
      referenceNumber: [null,Validators.required],
      mobileNumber: [
        null
      ],
      invoiceDate: [
        null,
        Validators.compose([Validators.required, CustomValidators.date])
      ],
      dueDate: [
        null,
        Validators.compose([ CustomValidators.date])
      ],
      userLoginId: [null],
      maximumAmountAllowed: [null],
      minimumAmountAllowed: [null],
      isPartially: [],
      invoiceId: [],
      radioPartyto: [],
      radioToName: [null],
  
      paymentWay:[]
    });
  }

  toPartyValidation() {
    this.form.controls["partyIdTo"].setValidators(Validators.required);
    this.form.controls["email"].setValidators(null);
    this.form.controls["mobileNumber"].setValidators(null);
    this.form.controls["toName"].setValidators(null);

    this.form.controls["email"].setValue(null);
    this.form.controls["mobileNumber"].setValue(null);
    this.form.controls["toName"].setValue(null);
  }
  toNameValidation() {
    this.form.controls["email"].setValidators([ Validators.compose([Validators.required,CustomValidators.email])]);
    this.form.controls["mobileNumber"].setValidators([
      Validators.compose([Validators.required]),
      Validators.pattern("[0-9]*")
    ]);
    this.form.controls["toName"].setValidators(Validators.required);
    this.form.controls["partyIdTo"].setValidators(null);
    this.form.controls["partyIdTo"].setValue("");

  }
  isTotalyValidation() {
    this.form.get('isPartially').setValue("N");
    this.form.controls["maximumAmountAllowed"].setValidators(null);
    this.form.controls["minimumAmountAllowed"].setValidators(null);
    this.form.controls["maximumAmountAllowed"].setValue(null);
    this.form.controls["minimumAmountAllowed"].setValue(null);
  }

  isPartiallyValidation() {
    this.form.get('isPartially').setValue("Y");
  this.form.controls["maximumAmountAllowed"].setValidators(Validators.required);
    this.form.controls["minimumAmountAllowed"].setValidators(Validators.required);
  }


  dueDateValidation(){
    var invoiceDate =this.form.get('invoiceDate').value;
    var dueDate =this.form.get('dueDate').value;
    if(invoiceDate>dueDate){
      this.showWarning("الرجاء تعبئة تاريح الاستحقاق بشكل صحيح")
      this.form.get('dueDate').setValue(null);
    }
    
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
}

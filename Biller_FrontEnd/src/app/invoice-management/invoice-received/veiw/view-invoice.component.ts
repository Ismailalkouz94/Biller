import {
  Component,
  OnInit,
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
import { Http, Headers, RequestOptions } from "@angular/http";
import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { ActivatedRoute, Router } from "@angular/router";
import { appConfig } from "../../../app.config";
// import {main}
// import { Notifications } from "../../../../notifications/notifications.service";
import { SelectItem } from "../../../shared/guards/tree/selectitem";
import { OnDestroy } from "@angular/core";

@Component({
  selector: "ms-view-invoice",
  templateUrl: "./view-invoice.component.html",
  styleUrls: ["../../../../assets/scss/myStyle.scss"]
})
export class ViewInvoiceComponent implements OnInit {
  disabel: boolean = true;
  invoiceList;
  userLoginId;
  favPartyList;
  updateInvoiceList;
  fromParty;
  public invoiceId;
  invoiceType;
  invoiceDate;
  dueDate;
  paidDate;
  option;
  partyTo: boolean;

  minimumAmount;
  maximumAmount;
  invoiceTypelist: Array<any> = [];
  statusId = "INVOICE_CREATED";
 
  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public http: Http,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    private router: Router,
    private route: ActivatedRoute // public notification:Notifications
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this.getInvoiceType();
    this.getFavParties();
    this.setFormValidation();
 this. getInvoiceItems();
    // this.route.queryParams.subscribe(params => {
    // 	this.getInvoiceById(params['id'])
    //   });
    this.getInvoiceById(localStorage.getItem("invoiceId"));
  }

  update() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    var link = appConfig.apiUrl + "invoice/update";
    var body = JSON.stringify(this.form.value);
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
          this.showError("حدث خطأ ما");
          // this.notification.showError("error occured");
        }
      );
  }

  hiddenField ="false";
  getInvoiceById(id) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: id } });
    this.http
      .get(appConfig.apiUrl + "invoice/findById", options)
      .map(res => res.json())
      .subscribe(result => {
        this.invoiceId=id;
          if (result.data.description != null) {
          this.form.get("description").setValue(result.data.description);
        }

     
        this.form.get("statusId").setValue(result.data.status.globalItemId);
        if (result.data.referenceNumber != null) {
          this.form
            .get("referenceNumber")
            .setValue(result.data.referenceNumber);
        }

        if (result.data.paidNumber != null) {
          this.form.get("paidNumber").setValue(result.data.paidNumber);
        }

     

        if (result.data.isPartially == "Y") {
          if (result.data.minimumAmountAllowed != null) {
            this.form.get("paymentWay").setValue("نعم");
            this.form
              .get("minimumAmountAllowed")
              .setValue(result.data.minimumAmountAllowed);
            this.minimumAmount = result.data.minimumAmountAllowed;
          }
          if (result.data.maximumAmountAllowed != null) {
            this.form
              .get("maximumAmountAllowed")
              .setValue(result.data.maximumAmountAllowed);
            this.maximumAmount = result.data.maximumAmountAllowed;
          }
        } else if (result.data.isPartially == "N") {
          this.form.get("paymentWay").setValue("لا");
          this.hiddenField = "true";
        
      
        }
            if (result.data.invoiceDate != null) {
          // this.form.get('invoiceDate').setValue(new Date());
          this.invoiceDate = result.data.invoiceDate;
        }

        if (result.data.dueDate != null) {
          this.dueDate = result.data.dueDate;
          // this.form.get('dueDate').setValue(result.data.dueDate);
        }

        if (result.data.order != null) {
          this.form.get("orderId").setValue(result.data.order.orderId);
        }


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
      });
  }
  totalPrice(quantity, unitPrice) {
    return Number(quantity) * Number(unitPrice);
  }
  invoiceItemsData;
  getInvoiceItems() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { invoiceId: localStorage.getItem("invoiceId")}
    });
    this.http
      .get(appConfig.apiUrl + "invoiceItem/findByInvoiceId", options)
      .map(res => res.json())
      .subscribe(data => {
        this.invoiceItemsData = data.data;
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
      fromPartyId: [null],
      toName: [null],
      description: [null, Validators.required],
      paidNumber: [null, Validators.required],
      statusId: [null],
      partyIdTo: [null, Validators.required],
      invoiceTypeId: [null, Validators.required],
      orderId: [null],
      email: [null],
      referenceNumber: [null],
      mobileNumber: [null],
      invoiceDate: [
        null,
        Validators.compose([Validators.required, CustomValidators.date])
      ],
      dueDate: [
        null,
        Validators.compose([Validators.required, CustomValidators.date])
      ],
      userLoginId: [null],
      maximumAmountAllowed: [null],
      minimumAmountAllowed: [null],
      isPartially: [],
      invoiceId: [],
      radioPartyto: [],
      radioToName: [null],
     
      paymentWay: []
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
}

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
import { fadeInAnimation } from "../../../../core/route-animation/route.animation";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { ActivatedRoute, Router } from "@angular/router";
import { appConfig } from "../../../../app.config";
// import {main}
// import { Notifications } from "../../../../notifications/notifications.service";
import { SelectItem } from "../../../../shared/guards/tree/selectitem";
import { OnDestroy } from "@angular/core";
import { ConfirmationDialog } from '../../../../confirm-dialog/confirmation-dialog';
import { MdDialog, MdDialogRef } from '@angular/material';
@Component({
  selector: "ms-update-invoice",
  templateUrl: "./update-invoice.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"]
})
export class UpdateInvoiceComponent implements OnInit {
 

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
  dialogRef: MdDialogRef<ConfirmationDialog>;
  minimumAmount;
  maximumAmount;
  invoiceTypelist: Array<any> = [];

 
  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public http: Http,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    private router: Router,
    public dialog: MdDialog,
    private route: ActivatedRoute // public notification:Notifications
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this.getInvoiceType();
    this.getFavParties();
    this.setFormValidation();
 
    // this.route.queryParams.subscribe(params => {
    // 	this.getInvoiceById(params['id'])
    //   });
    this.getInvoiceById(localStorage.getItem("invoiceId"));
  }

  update() {

    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "هل انت متأكد من عملية التعديل ؟"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
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
      this.dialogRef = null;
    });
  
  }

  getInvoiceById(id) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: id } });
    this.http
      .get(appConfig.apiUrl + "invoice/findById", options)
      .map(res => res.json())
      .subscribe(result => {
        this.invoiceId = id;
        this.form
          .get("userLoginId")
          .setValue(localStorage.getItem("userLoginId"));
        this.form.get("fromPartyId").setValue(result.data.fromParty.partyId);

        if (result.data.description != null) {
          this.form.get("description").setValue(result.data.description);
        }

        this.form.get('invoiceTypeId').setValue("INVOICE_ITEM");
        this.form.get("invoiceId").setValue(id);
        this.form.get("statusId").setValue(result.data.status.globalItemId);
        if (result.data.referenceNumber != null) {
          this.form
            .get("referenceNumber")
            .setValue(result.data.referenceNumber);
        }

        if (result.data.paidNumber != null) {
          this.form.get("paidNumber").setValue(result.data.paidNumber);
        }

        if (result.data.toParty != null) {
          this.form.get("partyIdTo").setValue(result.data.toParty.partyId);
          this.partyTo = false;
          this.toPartyValidation();
        }

        if (result.data.toName != null) {
          this.form.get("toName").setValue(result.data.toName);
          this.partyTo = true;
          this.toNameValidation();
        }
        if (result.data.paidDate != null) {
          this.form.get("paidDate").setValue(result.data.paidDate);
        }

        if (result.data.isPartially == "Y") {
          this.form.get("isPartially").setValue("Y");
          this.option = true;
          if (result.data.minimumAmountAllowed != null) {
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
          this.form.get("isPartially").setValue("N");
          this.option = false;
          this.isTotalyValidation();
        }
        if (result.data.email != null) {
          this.form.get("email").setValue(result.data.email);
        }
        if (result.data.mobileNumber != null) {
          this.form.get("mobileNumber").setValue(result.data.mobileNumber);
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

        // this.description = data.description;
        // this.globalItemId = data.globalItem.globalItemId;
        // this.orderDate = data.orderDate;
        // this.toPartyId = data.toParty.partyId;
        // this.fromPartyId = data.fromParty.partyId;
        // this.userLoginId = sessionStorage.getItem("userLoginId")
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
    this.form.controls["email"].setValidators([
      Validators.compose([Validators.required, CustomValidators.email])
    ]);
    this.form.controls["mobileNumber"].setValidators([
      Validators.compose([Validators.required]),
      Validators.pattern("[0-9]*")
    ]);
    this.form.controls["toName"].setValidators(Validators.required);
    this.form.controls["partyIdTo"].setValidators(null);
    this.form.controls["partyIdTo"].setValue("");
  }
  isTotalyValidation() {
    this.form.get("isPartially").setValue("N");
    this.form.controls["maximumAmountAllowed"].setValidators(null);
    this.form.controls["minimumAmountAllowed"].setValidators(null);
    this.form.controls["maximumAmountAllowed"].setValue(null);
    this.form.controls["minimumAmountAllowed"].setValue(null);
  }

  isPartiallyValidation() {
    this.form.get("isPartially").setValue("Y");
    this.form.controls["maximumAmountAllowed"].setValidators(
      Validators.required
    );
    this.form.controls["minimumAmountAllowed"].setValidators(
      Validators.required
    );
  }

  dueDateValidation() {
    var invoiceDate = this.form.get("invoiceDate").value;
    var dueDate = this.form.get("dueDate").value;
    if (invoiceDate > dueDate) {
      this.showWarning("الرجاء تعبئة تاريح الاستحقاق بشكل صحيح");
      this.form.get("dueDate").setValue(null);
    }
  }

  setFormValidation() {
    this.form = this.fb.group({
      fromPartyId: [null],
      toName: [null],
      description: [null, Validators.required],
      paidNumber: [null],
      statusId: [null],
      partyIdTo: [null, Validators.required],
      invoiceTypeId: [null, Validators.required],
      orderId: [null],
      email: [null],
      referenceNumber: [null,Validators.required],
      mobileNumber: [null],
      invoiceDate: [
        null,
        Validators.compose([Validators.required, CustomValidators.date])
      ],
      dueDate: [
        null,
        Validators.compose([CustomValidators.date])
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

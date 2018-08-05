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
import { Http, Headers, RequestOptions } from '@angular/http';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { appConfig } from '../../../../app.config';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: "ms-update-orderReceived",
  templateUrl: "./update-orderReceived.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"]
})
export class UpdateOrderReceivedComponent implements OnInit {
  showSelected: boolean;
  public form: FormGroup;
  btnLabel: String;
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public http: Http,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.toastr.setRootViewContainerRef(vcr);
    this.showSelected = false;
    this.btnLabel = "اضافة"
  }

  orderList;
  orderItems;

  orderId;
  name;
  description;
  globalItemId;
  orderDate;
  toPartyId;
  fromPartyId;
  userLoginId;

  favPartyList;


  createOrder() {
    console.log("Ok");
    this.showSelected = true;
    this.btnLabel = "تعديل"
  }

  showSuccess() {
    this.toastr.success("You are awesome!", "Success!");
  }
  showError() {
    this.toastr.error("This is not good!", "Oops!");
  }
  showWarning() {
    this.toastr.warning("You are being warned.", "Alert!");
  }
  showInfo() {
    this.toastr.info("Just some information for you.");
  }

  showCustom() {
    this.toastr.custom(
      '<span style="color: red">Message in red.</span>',
      null,
      { enableHTML: true }
    );
  }
  ngOnInit() {

    this.route.queryParams.subscribe(params => {
    });
    this.getOrderById(sessionStorage.getItem("orderId"))

    this.form = this.fb.group({
      orderId: [null],
      // orderCode: [null],
      name: [null, Validators.compose([Validators.required])],
      description: [null, Validators.compose([Validators.required])],
      globalItemId: [null, Validators.compose([Validators.required])],
      orderDate: [null, Validators.compose([Validators.required])],
      toPartyId: [null, Validators.compose([Validators.required])],
      fromPartyId: [null,],
      userLoginId: [null],
    });

    this.form.disable();
  }

  getOrderById(id) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: id } });
    this.http.get(appConfig.apiUrl + 'orders/findById', options).map(res => res.json()).subscribe((data) => {
      this.orderList = data.data;
      this.orderItems=this.orderList.orderItems;
      this.orderId = this.orderList.orderId;
      this.name = this.orderList.name;
      this.description = this.orderList.description;
      this.globalItemId = this.orderList.globalItem.globalItemId;
      this.orderDate = this.orderList.orderDate;
      this.toPartyId = this.orderList.toParty.partyId;
      this.fromPartyId = this.orderList.fromParty.partyGroup.tradeName;
      this.userLoginId = sessionStorage.getItem("userLoginId")
    })

  }

  totalPrice(quantity, unitPrice) {
    return Number(quantity) * Number(unitPrice);
  }
}

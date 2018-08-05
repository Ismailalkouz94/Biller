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
  selector: "ms-update-order",
  templateUrl: "./update-order.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"]
})
export class UpdateOrderComponent implements OnInit {
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

  orderStatus;

  orderId;
  orderCode;
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

  showSuccessWithMessage(message) {
    this.toastr.success(message, "Success!");
  }
  showError() {
    this.toastr.error("This is not good!", "Oops!");
  }
  showErrorWithMessage(message) {
    this.toastr.error(message, "Oops!");
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
    this.orderStatus = localStorage.getItem("orderStatus");

    this.route.queryParams.subscribe(params => {
    });
    this.getOrderById(sessionStorage.getItem("orderId"))

    this.form = this.fb.group({
      orderId: [null],
      orderCode: [null],
      name: [null, Validators.compose([Validators.required])],
      description: [null, Validators.compose([Validators.required])],
      globalItemId: [null, Validators.compose([Validators.required])],
      orderDate: [null, Validators.compose([Validators.required])],
      toPartyId: [null, Validators.compose([Validators.required])],
      fromPartyId: [null,],
      userLoginId: [null],
    });

    if(this.orderStatus=="COMPLETED" || this.orderStatus=="CANCELED"){
      this.form.disable();
    }
  }

  getOrderById(id) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: id } });
    this.http.get(appConfig.apiUrl + 'orders/findById', options).map(res => res.json()).subscribe((data) => {
      this.orderList = data.data;
      this.orderItems=this.orderList.orderItems;
      this.orderId = this.orderList.orderId;
      this.orderCode= this.orderList.orderCode;
      this.name = this.orderList.name;
      this.description = this.orderList.description;
      // this.globalItemId = "B_UPDATED";
      this.globalItemId =  this.orderList.globalItem.globalItemId;
      this.orderDate = this.orderList.orderDate;
      this.toPartyId = this.orderList.toParty.partyId;
      this.fromPartyId = this.orderList.fromParty.partyId;
      this.userLoginId = sessionStorage.getItem("userLoginId")
    })

    this.getFavParties();

  }

  getFavParties() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { partyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'partyGroupFav/listAllFavByParty', options).map(res => res.json()).subscribe((data) => {
      this.favPartyList = data;
    })
  }

  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    var link = appConfig.apiUrl + 'orders/update';
    var body = JSON.stringify(this.form.value);
    this.http.put(link, body, { headers }).map(res => res.json()).subscribe(data => {
      if (data.isError == false) {
        this.showSuccessWithMessage(data.messageAR)
      } else {
        this.showErrorWithMessage(data.messageEN);
      }
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }

  totalPrice(quantity, unitPrice) {
    return Number(quantity) * Number(unitPrice);
  }

}

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
import { appConfig } from '../../../../app.config';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: "ms-add-order",
  templateUrl: "./add-order.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"]
})
export class AddOrderComponent implements OnInit {
  showSelected: boolean;
  public form: FormGroup;
  btnLabel: String;
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public http: Http,
    private router: Router,

  ) {
    this.toastr.setRootViewContainerRef(vcr);
    this.showSelected = false;
    this.btnLabel = "اضافة"
  }

  edit = false;
  sent = false;

  fromPartyId = sessionStorage.getItem("partyId");
  userLoginId = sessionStorage.getItem("userLoginId");
  globalItemId = "CREATED";
  favPartyList;
  orderId;
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

    this.getFavParties();

    this.form = this.fb.group({
      orderCode: [null],
      name: [null, Validators.compose([Validators.required])],
      description: [null, Validators.compose([Validators.required])],
      globalItemId: [null, Validators.compose([Validators.required])],
      orderDate: [null, Validators.compose([Validators.required])],
      toPartyId: [null, Validators.compose([Validators.required])],
      fromPartyId: [null],
      userLoginId: [null],
      orderId: []
    });
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
    var body = JSON.stringify(this.form.value);
    if (this.edit == false) {
      var link = appConfig.apiUrl + 'orders/add';
      this.http.post(link, body, { headers }).map(res => res.json()).subscribe((data) => {
        if (data.isError == false) {
          sessionStorage.setItem("orderId", data.data.orderId);
          this.orderId = data.orderId;
          this.edit = true;
          this.showSelected = true;

          this.showSuccessWithMessage(data.messageAR)
        }
        else {
          this.showErrorWithMessage(data.messageEN);
        }
      }, error => {
        this.showError()
      })
    } else {
      var link = appConfig.apiUrl + 'orders/update';

      this.http.put(link, body, { headers }).map(res => res.json()).subscribe(data => {
        if (data.isError == false) {
          sessionStorage.setItem("orderId", data.date.orderId);
          this.showSuccessWithMessage(data.messageAR)
        } else {
          this.showErrorWithMessage(data.messageEN);
        }
      }, error => {
        this.showError()
        console.log("Oooops!");
      });
    }

  }


  doSent() {
    this.globalItemId="SENT"
  }

}
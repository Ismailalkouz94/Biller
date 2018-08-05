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

import { AddCategoryComponent } from './../../../../config/category/add/add-category.component';
import { TreeviewItem, TreeviewConfig } from '../../../../lib';

@Component({
  selector: "ms-order-item",
  templateUrl: "./update-order-item.component.html",
  styleUrls: ['../../../../../assets/scss/myStyle.scss'],
  providers:[AddCategoryComponent]


})
export class UpdateOrderItemComponent implements OnInit {
  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public category:AddCategoryComponent,
    public http: Http,
    private router: Router,
  ) {
    this.toastr.setRootViewContainerRef(vcr);
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
  value = 11;
	items: TreeviewItem[];
	config = TreeviewConfig.create({
		hasFilter: true,
		hasCollapseExpand: true
  });

  onValueChange(value: number) {
    console
  }
  ngOnInit() {
    this.items = this.category.getMainCategory();
    this.form = this.fb.group({
      partyGroupId: [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
      partyGroupCode: [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
      partyGroupName: [null, Validators.compose([Validators.required])],
      partyGroupType: [null, Validators.compose([Validators.required])],
      partyTaxId: [null, Validators.compose([Validators.required])],
      commericalRegistrationNum: [null, Validators.compose([Validators.required])],
      partyCapital: [null, Validators.compose([Validators.required])],
      partyActivity: [null, Validators.compose([Validators.required])],
      partySize: [null, Validators.compose([Validators.required])],
      monthlyInvoicingRate: [null, Validators.compose([Validators.required])],
      telephoneNumber1: [null, Validators.compose([Validators.required])],
      telephoneNumber2: [null, Validators.compose([Validators.required])],
      postalCode: [null, Validators.compose([Validators.required])],
      mailBox: [null, Validators.compose([Validators.required])],
      companyType: [null, Validators.compose([Validators.required])],
      partyType:[],
      partyId:[]
    });
  }


  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    var link = appConfig.apiUrl + 'orderItems/update';
    var body = JSON.stringify(this.form.value);
    this.http.put(link, body, { headers })
      .subscribe(data => {
        this.showSuccess()
        // this.router.navigate(['/transactions/order']);
      }, error => {
        this.showError()
        console.log("Oooops!");
      });
  }


}

import {Component,OnInit,ViewEncapsulation,ViewContainerRef} from "@angular/core";
import {FormBuilder,FormGroup,Validators,FormControl} from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { AddCategoryComponent } from '../../../../config/category/add/add-category.component';
import { TreeviewItem, TreeviewConfig } from '../../../../lib';
import { appConfig } from '../../../../app.config';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { Http, Headers, RequestOptions } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { quantile } from "d3";
import { MdDialog, MdDialogRef } from '@angular/material';
import { ConfirmationDialog } from '../../../../confirm-dialog/confirmation-dialog';

@Component({
  selector: "ms-order-itemReceived",
  templateUrl: "./add-order-item.component.html",
  styleUrls: ['../../../../../assets/scss/myStyle.scss'],
  providers: [AddCategoryComponent]
})
export class AddOrderItemReceivedComponent implements OnInit {
  public form: FormGroup;
  dialogRef: MdDialogRef<ConfirmationDialog>;
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public category: AddCategoryComponent,
    public http: Http,
    private router: Router,
    public dialog: MdDialog

  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  orderItemsDate;
  allItems;
  units;

  orderItemId;
  description;
  categoryId;
  categoryIdDesc;
  quantity = 1;
  price = 0;
  unitId = 0;
  unitIdDesc;
  itemId = 0;
  itemIdDesc;

  userLoginId = sessionStorage.getItem("userLoginId");
  orderId = sessionStorage.getItem("orderId");

  edit = false;

  orderStatus;
  showSuccess(message) {
    this.toastr.success(message, "Success!");
  }
  showError(message) {
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
  value = 11;
  items: TreeviewItem[];
  config = TreeviewConfig.create({
    hasFilter: true,
    hasCollapseExpand: true
  });
  onValueChange(value: number) {
    console.log('valueChange raised with value: ' + value);
  }
  ngOnInit() {

    this.orderStatus = localStorage.getItem("orderStatus");

    this.items = this.category.getMainCategory();
    this.form = this.fb.group({
      categoryId: [null, Validators.compose([Validators.required])],
      itemId: [null, Validators.compose([Validators.required])],
      description: [null, Validators.compose([Validators.required])],
      unitPrice: [null, Validators.compose([Validators.required])],
      quantity: [null, Validators.compose([Validators.required])],
      unitId: [null, Validators.compose([Validators.required])],
      userLoginId: [null, Validators.compose([Validators.required])],
      orderId: [null, Validators.compose([Validators.required])],
      orderItemId: [null]

    });
    this.getUnits();
    this.getOrderItems();
    this.getItems();
  }

  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    var link = appConfig.apiUrl + 'orderItems/update';
    var body = JSON.stringify(this.form.value);
    this.http.put(link, body, { headers }).map(res => res.json()).subscribe((data) => {
      if (data.isError == false) {
        this.edit = false;
        this.getOrderItems();
        this.showSuccess(data.messageAR)
        this.changeStatus();
      } else {
        this.showError(data.messageEN);
      }
    }, error => {
      this.showError("Error in Server")
    })
  }

  getOrderItems() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { orderId: this.orderId } });
    this.http.get(appConfig.apiUrl + 'orderItems/findByOrderId', options).map(res => res.json()).subscribe((data) => {
      this.orderItemsDate = data.data;
    })
  }

  //for DropDown List
  getCategory() {

  }

  //for DropDown List
  getItems() {
    // let headers = new Headers();
    // headers.append("Content-Type", "application/json");
    // let options = new RequestOptions({ headers: headers, params: { CatId: this.form.value.categoryId } });
    // this.http.get(appConfig.apiUrl + 'items/findAll', options).map(res => res.json()).subscribe((data) => {
    //   this.allItems = data;
    // })

    this.http.get(appConfig.apiUrl + 'items/findAll').map(data => data.json()).subscribe((data) => {
      this.allItems = data;
    })
  }

  //for DropDown List
  getUnits() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { partyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'units/findAll', options).map(res => res.json()).subscribe((data) => {
      this.units = data;
    })
  }

  getItemUnitPrice() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { unitId: this.unitId, itemId: this.itemId } });
    this.http.get(appConfig.apiUrl + 'itemUnitPrice/findByUnique', options).map(data => data.json()).subscribe((data) => {
      this.price = data.price;
    })
  }

  totalPrice(quantity, unitPrice) {
    return Number(quantity) * Number(unitPrice);
  }

  doUpdate(orderItem) {
    this.orderItemId = orderItem.orderItemId;
    this.description = orderItem.description;
    this.categoryId = orderItem.category.categoryId;
    this.categoryIdDesc = orderItem.category.description
    this.itemId = orderItem.item.itemId;
    this.itemIdDesc = orderItem.item.brandName;
    this.unitId = orderItem.unit.unitId;
    this.unitIdDesc = orderItem.unit.name;
    this.quantity = orderItem.quantity;
    this.price = orderItem.unitPrice;

    this.edit = true;
  }

  changeStatus() {
    let url = appConfig.apiUrl + 'orders/changeStatus';
    var headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('orderId', sessionStorage.getItem("orderId"));
    urlSearchParams.append('status', 'C_UPDATED');
    let body = urlSearchParams.toString()
    this.http.post(url, body, { headers: headers }).
      subscribe(data => {
      }, error => {
      })
  }

  openConfirmationDialog(id) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to delete?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.delete(id);
      }
      this.dialogRef = null;
    });
  }

  delete(id) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: id } });
    this.http.delete(appConfig.apiUrl + 'orderItems/delete', options).map(data => data.json()).subscribe((data) => {
      if (data.isError == false) {
        this.showSuccess(data.messageAR);
        this.getOrderItems();
      } else {
        this.showError(data.messageEN);
      }
    }, error => {
      this.showError("Oooops! Error in Server ")
    });
  }

  cancel() {
    this.edit = false;
  }

}

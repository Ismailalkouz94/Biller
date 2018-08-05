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
  selector: "ms-order-itemSent",
  templateUrl: "./add-order-item.component.html",
  styleUrls: ['../../../../../assets/scss/myStyle.scss'],
  providers: [AddCategoryComponent]


})
export class AddOrderItemSentComponent implements OnInit {
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
  quantity = 1;
  price = 0;
  unitId = 0;
  itemId = 0;

  userLoginId = sessionStorage.getItem("userLoginId");
  orderId = sessionStorage.getItem("orderId");

  edit = false;

  orderStatus;

  addOrUpdate = false;
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
    this.categoryId = value;
    this.getItems(this.categoryId);


  }

  ngOnInit() {
    this.orderStatus = localStorage.getItem("orderStatus");

    this.items = this.category.getMainCategory();
    this.form = this.fb.group({
      categoryId: [],
      itemId: [null, Validators.compose([Validators.required])],
      description: [null, Validators.compose([Validators.required])],
      unitPrice: [null, Validators.compose([Validators.required])],
      quantity: [null, Validators.compose([Validators.required])],
      unitId: [null, Validators.compose([Validators.required])],
      userLoginId: [null],
      orderId: [null],
      orderItemId: [null]

    });

    this.getUnits();
    this.getOrderItems();
    // this.getItems();
  }


  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    if (this.edit) {
      var link = appConfig.apiUrl + 'orderItems/update';
      var body = JSON.stringify(this.form.value);
      this.http.put(link, body, { headers }).map(res => res.json()).subscribe((data) => {
        if(data.isError == false){
        this.cancel();
        this.getOrderItems();
        this.showSuccess(data.messageAR)
        this.changeStatus();
        this.form.controls["userLoginId"].setValue(sessionStorage.getItem("userLoginId"));
        this.form.controls["orderId"].setValue(sessionStorage.getItem("orderId"));
        }else{
          this.showError(data.messageEN);
        }
      }, error => {
        this.showError("Error in Server");
      })
    } else {
      var link = appConfig.apiUrl + 'orderItems/add';
      var body = JSON.stringify(this.form.value);
      this.http.post(link, body, { headers }).map(res => res.json()).subscribe((data) => {
        if (data.isError == false) {
          this.getOrderItems();
          this.showSuccess(data.messageAR);
          this.changeStatus();
          this.form.reset();
          this.form.controls["userLoginId"].setValue(sessionStorage.getItem("userLoginId"));
          this.form.controls["orderId"].setValue(sessionStorage.getItem("orderId"));
        } else {
          this.showError(data.messageEN);
        }
      }, error => {
        this.showError("Error in Server");
      })
    }
  }

  changeStatus() {
    console.log(this.orderStatus);
    if (this.orderStatus != "CREATED") {
      let url = appConfig.apiUrl + 'orders/changeStatus';
      var headers = new Headers();
      headers.append('Content-Type', 'application/x-www-form-urlencoded');
      let urlSearchParams = new URLSearchParams();
      urlSearchParams.append('orderId', sessionStorage.getItem("orderId"));
      urlSearchParams.append('status', 'B_UPDATED');
      let body = urlSearchParams.toString()
      this.http.post(url, body, { headers: headers }).
        subscribe(data => {
        }, error => {
        })
    }
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
  getItems(categoryId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { catId: categoryId } });
    this.http.get(appConfig.apiUrl + 'items/findByCatId', options).map(res => res.json()).subscribe((data) => {
      this.allItems = data;
    })

    // this.http.get(appConfig.apiUrl + 'items/findAll').map(data => data.json()).subscribe((data) => {
    //   this.allItems = data;
    // })

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
    this.form.controls['categoryId'].setValue(orderItem.category.categoryId);
    this.getItems(orderItem.category.categoryId);
    // this.items.correctChecked('d');
    this.itemId = orderItem.item.itemId;
    this.unitId = orderItem.unit.unitId;
    this.quantity = orderItem.quantity;
    this.price = orderItem.unitPrice;
    this.userLoginId = sessionStorage.getItem("userLoginId");
    this.orderId = sessionStorage.getItem("orderId");
    this.edit = true;
    this.addOrUpdate = true;
  }

  doAddOrUpdate() {
    this.addOrUpdate = true;
  }


  cancel() {
    this.edit = false;
    this.addOrUpdate = false;
    // this.form.reset();
    this.form.controls['orderItemId'].reset();
    this.form.controls['categoryId'].reset();
    this.form.controls['itemId'].reset();
    this.form.controls['unitId'].reset();
    this.form.controls['quantity'].reset();
    this.form.controls['unitPrice'].reset();
    // this.form.controls['categoryId'].setValue(sessionStorage.getItem("orderId"));
    // this.orderId = sessionStorage.getItem("orderId");
    // this.userLoginId = sessionStorage.getItem("userLoginId");
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
      if(data.isError == false){
      this.showSuccess(data.messageAR);
      this.getOrderItems();
      }else{
        this.showError(data.messageEN);
      }
    }, error => {
      this.showError("Oooops! Error in Server ")
    });

  }

}

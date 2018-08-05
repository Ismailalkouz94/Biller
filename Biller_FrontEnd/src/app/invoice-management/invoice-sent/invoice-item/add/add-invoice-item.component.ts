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
import { AddCategoryComponent } from "../../../../config/category/add/add-category.component";
import { TreeviewItem, TreeviewConfig } from "../../../../lib";
import { appConfig } from "../../../../app.config";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { Http, Headers, RequestOptions } from "@angular/http";
import { ActivatedRoute, Router } from "@angular/router";
import { quantile } from "d3";
import { messages } from "../../../../dashboard/dashboard.data";

@Component({
  selector: "ms-invoice-item",
  templateUrl: "./add-invoice-item.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"],
  providers: [AddCategoryComponent]
})
export class AddInvoiceItemComponent implements OnInit {
  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public category: AddCategoryComponent,
    public http: Http,
    private router: Router
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  invoiceItemsData;
  allItems;
  units;

  categoryVal;
  invoiceItemId;
  description;
  categoryId;
  quantity = 1;
  price = 0;
  unitId = 0;
  itemId = 0;

  userLoginId;
  invoiceId;

  edit = false;
  add = true;

  items: TreeviewItem[];
  config = TreeviewConfig.create({
    hasFilter: true,
    hasCollapseExpand: true
  });
  onValueChange(value: number) {
    this.categoryId = value;
    this.getItems(this.categoryId);
    this.form.get("unitId").setValue("");
    this.price = 0;
  }
  ngOnInit() {
    this.items = this.category.getMainCategory();
    this.userLoginId = localStorage.getItem("userLoginId");
    this.invoiceId = localStorage.getItem("invoiceId");
    this.form = this.fb.group({
      categoryId: [],
      itemId: [],
      description: [null, Validators.compose([Validators.required])],
      unitPrice: [null, Validators.compose([Validators.required])],
      quantity: [null, Validators.compose([Validators.required])],
      unitId: [],
      userLoginId: [null],
      invoiceId: [null],
      invoiceItemId: [null]
    });

    this.getUnits();
    this.getInvoiceItems();
  }

  onSubmit() {
    this.form.get("userLoginId").setValue(localStorage.getItem("userLoginId"));
    this.form.get("invoiceId").setValue(localStorage.getItem("invoiceId"));
    this.invoiceId=localStorage.getItem("invoiceId");
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    if (this.edit) {
      var link = appConfig.apiUrl + "invoiceItem/update";
      var body = JSON.stringify(this.form.value);

      this.http
        .put(link, body, { headers })
        .map(res => res.json())
        .subscribe(
          data => {
            this.getInvoiceItems();
            this.showSuccess(data.messageAR);
            this.form.reset();
            this.categoryVal=0;
            this.userLoginId = localStorage.getItem("userLoginId");
         
            // this.invoiceId = localStorage.getItem("invoiceId");
          },
          error => {
            this.showError("حدث خطأ ما في اضافة الفاتورة");
          }
        );
    } else {
      var link = appConfig.apiUrl + "invoiceItem/add";
      var body = JSON.stringify(this.form.value);
      this.http
        .post(link, body, { headers })
        .map(res => res.json())
        .subscribe(
          data => {
            this.getInvoiceItems();
            this.showSuccess(data.messageAR);
            this.form.reset();
           this.categoryVal=0;
          
            
          },
          error => {
            this.showError("حدث خطأ ما في اضافة الفاتورة");
          }
        );
    }
  }

  getInvoiceItems() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { invoiceId: localStorage.getItem("invoiceId") }
    });
    this.http
      .get(appConfig.apiUrl + "invoiceItem/findByInvoiceId", options)
      .map(res => res.json())
      .subscribe(data => {
        this.invoiceItemsData = data.data;
      });
  }

  //for DropDown List

  getItems(categoryId) {
    if (categoryId != null) {
      console.log(categoryId + "join");
      let headers = new Headers();
      headers.append("Content-Type", "application/json");

      let options = new RequestOptions({
        headers: headers,
        params: { catId: categoryId }
      });
      this.http
        .get(appConfig.apiUrl + "items/findByCatId", options)
        .map(res => res.json())
        .subscribe(data => {
          this.allItems = data;
        });
    }
  }

  //for DropDown List
  getUnits() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { partyId: localStorage.getItem("partyId") }
    });
    this.http
      .get(appConfig.apiUrl + "units/findAll", options)
      .map(res => res.json())
      .subscribe(data => {
        this.units = data;
      });
  }

  getItemUnitPrice() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    this.unitId = this.form.get("unitId").value;
    this.itemId = this.form.get("itemId").value;
    if (this.unitId != null && this.itemId != null) {
      let options = new RequestOptions({
        headers: headers,
        params: { unitId: this.unitId, itemId: this.itemId }
      });
      this.http
        .get(appConfig.apiUrl + "itemUnitPrice/findByUnique", options)
        .map(data => data.json())
        .subscribe(data => {
          if (data.price !== null) {
            this.price = data.price;
          } else {
            this.price = 0;
          }
        });
    } else {
      this.price = 0;
    }
  }

  totalPrice(quantity, unitPrice) {
    return Number(quantity) * Number(unitPrice);
  }
  cancel() {
    this.edit = false;
    this.form.reset();
  }

  //for DropDown List

  doUpdate(invoiceItem) {
    console.log(",,,,,,,");
    console.log(invoiceItem);
    this.invoiceItemId = invoiceItem.invoiceItemId;

    this.form.get("description").setValue(invoiceItem.description);
    if (invoiceItem.category != null) {
      // this.form.get('categoryId').setValue(invoiceItem.category.categoryId);
      this.categoryVal = invoiceItem.category.categoryId;
      console.log("caaa" + this.categoryVal);
    }

    if (invoiceItem.item != null) {
      this.getItems(this.categoryVal);
      this.form.get("itemId").setValue(invoiceItem.item.itemId);
    }
    if (invoiceItem.unit != null) {
      this.form.get("unitId").setValue(invoiceItem.unit.unitId);
    }

    this.form.get("quantity").setValue(invoiceItem.quantity);

    this.price = invoiceItem.unitPrice;

    this.edit = true;
    this.add = false;
  }

  delete(id) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: id } });

    this.http
      .delete(appConfig.apiUrl + "invoiceItem/delete", options)
      .map(res => res.json())
      .subscribe(
        data => {
          this.showSuccess(data.messageAR);
          this.getInvoiceItems();
        },
        error => {
          this.showError("حدث خطأ ما ");
        }
      );
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

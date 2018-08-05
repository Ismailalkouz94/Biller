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
import { TreeviewItem, TreeviewConfig } from '../../../lib';
import { Http, Headers, RequestOptions } from '@angular/http';
import { appConfig } from '../../../app.config';
import { Router } from '@angular/router';

import { AddCategoryComponent } from './../../category/add/add-category.component';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { MdDialog, MdDialogRef } from '@angular/material';
import { ConfirmationDialog } from '../../../confirm-dialog/confirmation-dialog';
@Component({
  selector: "ms-main-category",
  templateUrl: "./main-category.component.html",
  styleUrls: ["../../../../assets/scss/myStyle.scss"],
  providers: [AddCategoryComponent]
})
export class MainCategoryComponent implements OnInit {


  public form: FormGroup;
  dialogRef: MdDialogRef<ConfirmationDialog>;
  
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public http: Http,
    private router: Router,
    public category: AddCategoryComponent,
    public dialog: MdDialog
  ) {
    this.toastr.setRootViewContainerRef(vcr);

  }
  categoryDate;

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
    this.items = this.category.getMainCategory();
    this.form = this.fb.group({
      categoryId: [null],
      parentCategoryId: [null],
      description: [null],
      // partyId: [null],
    });
    this.getCategoryData();
  }

  openConfirmationDialog(catId) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to delete?"

    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.delete(catId);
      }
      this.dialogRef = null;
    });
  }

  delete(catId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: catId } });
    this.http.delete(appConfig.apiUrl + 'category/delete', options).subscribe(data => {
      this.showSuccess();
      this.getCategoryData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }

  update(catId) {
    this.router.navigate(['/config/update-category'], { queryParams: { id: catId } });
  }



  getCategoryData() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { partyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'category/findByPartyId', options).map(categoryDate => categoryDate.json()).subscribe((categoryDate) => {
      this.categoryDate = categoryDate;
    })
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



  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: this.form.value });

    this.http.get(appConfig.apiUrl + 'category/findByCriteria', options).map(res => res.json()).subscribe((categoryDate) => {
      this.categoryDate = categoryDate;
      for (let item in categoryDate) {
        console.log("from  submit1" + item);
      }
    })

  }




}

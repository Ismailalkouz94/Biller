import {Component,OnInit,ViewEncapsulation,ViewContainerRef
} from "@angular/core";
import {FormBuilder,FormGroup,Validators,FormControl} from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { AddCategoryComponent } from './../../category/add/add-category.component';
import { TreeviewItem, TreeviewConfig } from '../../../lib';
import { BookService } from '../../../shared/guards/book.service';
import { Http, Headers, RequestOptions } from '@angular/http';
import { appConfig } from '../../../app.config';
import { Router } from '@angular/router';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { MdDialog, MdDialogRef } from '@angular/material';
import { ConfirmationDialog } from '../../../confirm-dialog/confirmation-dialog';
@Component({
  selector: "ms-main-item",
  templateUrl: "./main-item.component.html",
  styleUrls: ["../../../../assets/scss/myStyle.scss"],
    providers: [
        BookService,
        AddCategoryComponent
    ]
})
export class MainItemComponent implements OnInit {
  public form: FormGroup;
  dialogRef: MdDialogRef<ConfirmationDialog>;

  constructor(private fb: FormBuilder,
     public toastr: ToastsManager,
      vcr: ViewContainerRef,
    private bookService: BookService,
    public http: Http,
    private router:Router,
    public category: AddCategoryComponent,
    public dialog: MdDialog
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }
  itemsList;


  value = 11;
	items: TreeviewItem[];
	config = TreeviewConfig.create({
	  hasFilter: true,
	  hasCollapseExpand: true
  });
  onValueChange(value: number) {
    console.log('valueChange raised with value: ' + value);
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
    this.items = this.category.getMainCategory();
    this.form = this.fb.group({
      unitId: [null],
      name: [null],
      partyId: [null],
    });

    this.getitemsData();
  }

  getitemsData() {
    this.http.get(appConfig.apiUrl +'items/findAll').map(res => res.json()).subscribe((itemsList) => {
      this.itemsList = itemsList;
    })
  }


  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: this.form.value });

    this.http.get(appConfig.apiUrl +'items/findByCriteria', options).map(res => res.json()).subscribe((itemsList) => {
      this.itemsList = itemsList;
      for (let item in itemsList) {
        console.log("from  submit1" + item);
      }
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

  delete(itemId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: itemId } });

    this.http.delete(appConfig.apiUrl +'items/delete', options).subscribe(data => {
      this.showSuccess()
     this.getitemsData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }
  update(itemId){
    this.router.navigate(['/config/update-item'],{ queryParams: { id: itemId } });
  }
  // , { queryParams: { page: pageNum } }
 
     

   
}

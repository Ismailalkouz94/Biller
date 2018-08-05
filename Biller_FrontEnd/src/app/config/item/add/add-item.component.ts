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
import { AddCategoryComponent } from './../../category/add/add-category.component';
import { TreeviewItem, TreeviewConfig } from '../../../lib';

import { PageTitleService } from '../../../core/page-title/page-title.service';
import { appConfig } from '../../../app.config';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { Http, Headers } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: "ms-item",
  templateUrl: "./add-item.component.html",
  styleUrls: ['../../../../assets/scss/myStyle.scss'],
  providers: [AddCategoryComponent]
})
export class AddItemComponent implements OnInit {


  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    private pageTitleService: PageTitleService,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public category: AddCategoryComponent,
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
  ngOnInit() {
    this.items = this.category.getMainCategory();
    this.form = this.fb.group({
      categoryId: [null, Validators.compose([Validators.required])],
      brandName: [null, Validators.compose([Validators.required])],
      description: [null,]
    });
  }

  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");

    var link = appConfig.apiUrl + 'items/add';
    var body = JSON.stringify(this.form.value);

    this.http.post(link, body, { headers })
      .subscribe(data => {
        this.showSuccess()
        this.router.navigate(['/config/item']);
      }, error => {
        this.showError()
        console.log("Oooops!");
      })
  }


  value = 11;
  items: TreeviewItem[];
  config = TreeviewConfig.create({
    hasFilter: true,
    hasCollapseExpand: true
  });

  onValueChange(value: number) {
    this.form.controls['categoryId'].setValue(value);
  }

}

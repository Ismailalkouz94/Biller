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

import { PageTitleService } from '../../../core/page-title/page-title.service';

import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { AddCategoryComponent } from './../../category/add/add-category.component';
import { TreeviewItem, TreeviewConfig } from '../../../lib';
import { Http, Headers, RequestOptions } from '@angular/http';
import { appConfig } from '../../../app.config';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: "ms-update-item",
  templateUrl: "./update-item.component.html",
  styleUrls: ['../../../../assets/scss/myStyle.scss'],
  providers: [AddCategoryComponent]
})
export class UpdateItemComponent implements OnInit {
  public form: FormGroup;
  constructor(
    private fb: FormBuilder, private pageTitleService: PageTitleService,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public category: AddCategoryComponent, public http: Http,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }
  value = 11;
  items: TreeviewItem[];
  config = TreeviewConfig.create({
    hasFilter: true,
    hasCollapseExpand: true
  });

  itemId: any;
  categoryId: any;
  brandName: any;
  description: any;


  onValueChange(value: number) {
    this.categoryId = value;
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
      itemId: [null],
      categoryId: [null, Validators.compose([Validators.required])],
      brandName: [null, Validators.compose([Validators.required])],
      description: [null, Validators.compose([Validators.required])]
    });

    this.route.queryParams.subscribe(params => {
      this.getItemById(params['id'])
    });
  }

  getItemById(id) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: id } });
    this.http.get(appConfig.apiUrl + 'items/findById', options).map(res => res.json()).subscribe((data) => {
      this.brandName = data.brandName
      this.categoryId = data.category.categoryId
      this.description = data.description
      this.itemId = data.itemId
    })

  }

  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");

    var link = appConfig.apiUrl + 'items/update';
    var body = JSON.stringify(this.form.value);
    this.http.put(link, body, { headers })
      .subscribe(data => {
        this.showSuccess()
        this.router.navigate(['/config/item']);
      }, error => {
        this.showError()
        console.log("Oooops!");
      })
  }


}

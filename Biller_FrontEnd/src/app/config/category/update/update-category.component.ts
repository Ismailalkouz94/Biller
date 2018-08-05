import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../../../core/page-title/page-title.service';
import { ActivatedRoute,Router } from '@angular/router';
import { AddCategoryComponent } from './../../category/add/add-category.component';
import { TreeviewItem, TreeviewConfig } from '../../../lib';
import { Http, Headers ,RequestOptions} from '@angular/http';
import { appConfig } from '../../../app.config';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
@Component({
	selector: 'ms-add-category',
	templateUrl: './update-category.component.html',
	styleUrls: ['../../../../assets/scss/myStyle.scss'],
	providers: [AddCategoryComponent]
})
export class UpdateCategoryComponent implements OnInit {

	public form: FormGroup;
	constructor(private fb: FormBuilder,
		 private pageTitleService: PageTitleService
		, public toastr: ToastsManager,
		 vcr: ViewContainerRef,
		  public http: Http,
		  private router:Router,
		  private route: ActivatedRoute,
		  public category: AddCategoryComponent,
		) {
		this.toastr.setRootViewContainerRef(vcr);
	}
	posts;
	result;

	categoryId: any;
	parentCategoryId: any;
	description: any;
	partyId: any;
	
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
		this.toastr.success('You are awesome!', 'Success!');
	}
	showError() {
		this.toastr.error('This is not good!', 'Oops!');
	}
	showWarning() {
		this.toastr.warning('You are being warned.', 'Alert!');
	}
	showInfo() {
		this.toastr.info('Just some information for you.');
	}

	showCustom() {
		this.toastr.custom('<span style="color: red">Message in red.</span>', null, { enableHTML: true });
	}
	ngOnInit() {
		this.items = this.category.getMainCategory();
		this.route.queryParams.subscribe(params => {
			this.getCategoryById(params['id'])
		  });

		this.form = this.fb.group({
			categoryId: [null],
			parentCategoryId: [null],
			description:[null, Validators.compose([Validators.required])],
			partyId: [null],
		});


	}


	getCategoryById(id) {
		let headers = new Headers();
		headers.append("Content-Type", "application/json");
		let options = new RequestOptions({ headers: headers, params: { id: id , partyId:sessionStorage.getItem("partyId") } });
		this.http.get(appConfig.apiUrl + 'category/findById', options).map(res => res.json()).subscribe((data) => {
		  this.categoryId = data.categoryId
		  this.parentCategoryId = data.parentCategoryId
		  this.partyId = data.party.partyId
		  this.description = data.party.description
		})
		
	  }

	onSubmit() {
		let headers = new Headers();
		headers.append("Content-Type", "application/json");
		var link = appConfig.apiUrl +'category/update';
		var body = JSON.stringify(this.form.value);

		console.log("data before req " + body);
		this.http.put(link, body, { headers })
			.subscribe(data => {
				this.router.navigate(['/config/category']);
			}, error => {
				this.showError()
				console.log("Oooops!");
			});
	}
}







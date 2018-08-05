import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../../../core/page-title/page-title.service';
import { Http, Headers } from '@angular/http';
import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { appConfig } from '../../../app.config';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { TreeviewItem, TreeviewConfig } from '../../../lib';
import { ActivatedRoute, Router } from '@angular/router';
import { Session } from 'selenium-webdriver';

@Component({
	selector: 'ms-add-unit',
	templateUrl: './add-unit.component.html',
	styleUrls: ['../../../../assets/scss/myStyle.scss']
})
export class AddUnitComponent implements OnInit {
	public form: FormGroup;
	constructor(
		private fb: FormBuilder,
		private pageTitleService: PageTitleService,
		public toastr: ToastsManager,
		vcr: ViewContainerRef, public http: Http,
		private router: Router,
	) {
		this.toastr.setRootViewContainerRef(vcr);
	}
	posts;
	result;
	partyId = sessionStorage.getItem("partyId");
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

		this.form = this.fb.group({
			name: [null, Validators.compose([Validators.required])],
			partyId: [null],
		});
		

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

		var link = appConfig.apiUrl + 'units/add';
		var body = JSON.stringify(this.form.value);

		this.http.post(link, body, { headers })
			.subscribe(data => {
				this.showSuccess()
				this.router.navigate(['/config/unit']);
			}, error => {
				this.showError()
				console.log("Oooops!");
			})
	}


}




import { Component, OnInit, ViewEncapsulation, ViewContainerRef, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../../../core/page-title/page-title.service';
import { MainUnitComponent } from '../search/main-unit.component';

import { Http, Headers, RequestOptions } from '@angular/http';
import { appConfig } from '../../../app.config';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastsManager } from 'ng2-toastr/ng2-toastr';
@Component({
	selector: 'ms-update-unit',
	templateUrl: './update-unit.component.html',
	styleUrls: ['../../../../assets/scss/myStyle.scss'],
	providers: [
		MainUnitComponent
	]
})
export class UpdateUnitComponent implements OnInit {

	public form: FormGroup;
	constructor(private fb: FormBuilder, private pageTitleService: PageTitleService
		, public toastr: ToastsManager,
		vcr: ViewContainerRef,
		public http: Http,
		private route: ActivatedRoute,
		private router: Router,
		private mainUnitComponent: MainUnitComponent
	) {
		this.toastr.setRootViewContainerRef(vcr);
	}
	posts;
	result;

	unitId: any;
	name: any;
	partyId;

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
		this.route.queryParams.subscribe(params => {
			this.getIUnitById(params['id'])
		});

		this.form = this.fb.group({
			unitId: [null],
			name: [null],
			partyId: [null],
		});

		console.log("sessiion Data");
		console.log(sessionStorage.getItem("unit"));
	}
	getIUnitById(id) {
		let headers = new Headers();
		headers.append("Content-Type", "application/json");
		let options = new RequestOptions({ headers: headers, params: { id: id, partyId: sessionStorage.getItem("partyId") } });
		this.http.get(appConfig.apiUrl + 'units/findById', options).map(res => res.json()).subscribe((data) => {
			this.unitId = data.unitId
			this.name = data.name
			this.partyId = data.party.partyId
		})

	}
	onSubmit() {
		let headers = new Headers();
		headers.append("Content-Type", "application/json");

		var link = appConfig.apiUrl + 'units/update';
		var body = JSON.stringify(this.form.value);

		this.http.put(link, body, { headers })
			.subscribe(data => {
				this.showSuccess()
				this.router.navigate(['/config/unit']);
			}, error => {
				this.showError()
				console.log("Oooops!");
			});
	}
}







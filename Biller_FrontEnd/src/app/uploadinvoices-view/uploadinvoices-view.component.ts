import { Component, OnInit,ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
import {fadeInAnimation} from "../core/route-animation/route.animation";
const password = new FormControl('', Validators.required);
const confirmPassword = new FormControl('', CustomValidators.equalTo(password));

@Component({
    selector: 'ms-uploadinvoices-view',
    templateUrl: './uploadinvoices-view.component.html',
    styleUrls: ['../../assets/scss/myStyle.scss'],
    encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },
    animations: [ fadeInAnimation ]
})
export class UploadInvoicesViewComponent implements OnInit {

	public form: FormGroup;
  	constructor(private fb: FormBuilder) {}
  	ngOnInit() {
    	this.form = this.fb.group({
      	fname: [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
      	email: [null, Validators.compose([Validators.required, CustomValidators.email])],
      	range: [null, Validators.compose([Validators.required, CustomValidators.range([5, 9])])],
      	url: [null, Validators.compose([Validators.required, CustomValidators.url])],
      	date: [null, Validators.compose([Validators.required, CustomValidators.date])],
      	creditCard: [null, Validators.compose([Validators.required, CustomValidators.creditCard])],
      	phone: [null, Validators.compose([Validators.required, CustomValidators.phone('en-US')])],
      	gender: [null, Validators.required],
      	password: password,
      	confirmPassword: confirmPassword
    	});
  	}  




    uploader: FileUploader = new FileUploader({url: 'https://evening-anchorage-3159.herokuapp.com/api/'});
    hasBaseDropZoneOver = false;
    hasAnotherDropZoneOver = false;

    fileOverBase(e: any): void {
        this.hasBaseDropZoneOver = e;
    }

    fileOverAnother(e: any): void {
        this.hasAnotherDropZoneOver = e;
    }
}




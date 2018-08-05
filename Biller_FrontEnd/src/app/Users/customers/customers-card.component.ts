// import { Component, Input, OnInit, ChangeDetectionStrategy ,ViewEncapsulation} from '@angular/core';
// import {fadeInAnimation} from "../../core/route-animation/route.animation";
// import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
// import { ICustomer } from '../shared/interfaces';
// import { TrackByService } from '../core/services/trackby.service';

// import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
// import { CustomValidators } from 'ng2-validation';


// const password = new FormControl('', Validators.required);
// const confirmPassword = new FormControl('', CustomValidators.equalTo(password));

// @Component({
//   selector: 'cm-customers-card',
//   templateUrl: './customers-card.component.html',
//   styleUrls: [ './customers-card.component.css' ],
//     encapsulation: ViewEncapsulation.None,
//     host: {
//         "[@fadeInAnimation]": 'true'
//     },
//     animations: [ fadeInAnimation ],
//   changeDetection: ChangeDetectionStrategy.OnPush
// })
// export class CustomersCardComponent implements OnInit {

//   @Input() customers: ICustomer[] = [];

// 	public form: FormGroup;
//   closeResult: string;
// 	constructor(private modalService: NgbModal,public trackbyService: TrackByService,private fb: FormBuilder) {}

//   	ngOnInit() {

//     	this.form = this.fb.group({
//       	fname: [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
//       	email: [null, Validators.compose([Validators.required, CustomValidators.email])],
//       	range: [null, Validators.compose([Validators.required, CustomValidators.range([5, 9])])],
//       	url: [null, Validators.compose([Validators.required, CustomValidators.url])],
//       	date: [null, Validators.compose([Validators.required, CustomValidators.date])],
//       	creditCard: [null, Validators.compose([Validators.required, CustomValidators.creditCard])],
//       	phone: [null, Validators.compose([Validators.required, CustomValidators.phone('en-US')])],
//       	gender: [null, Validators.required],
//       	password: password,
//       	confirmPassword: confirmPassword
//     	});
//   	}  

//       open(content) {
//     this.modalService.open(content).result.then((result) => {
//       this.closeResult = `Closed with: ${result}`;
//     }, (reason) => {
//       this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
//     });
//   }

//   private getDismissReason(reason: any): string {
//     if (reason === ModalDismissReasons.ESC) {
//       return 'by pressing ESC';
//     } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
//       return 'by clicking on a backdrop';
//     } else {
//       return  `with: ${reason}`;
//     }
//   }





// }


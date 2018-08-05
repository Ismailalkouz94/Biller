// import { Component, OnInit } from '@angular/core';

// import { DataService } from '../core/services/data.service';
// import { ICustomer, IPagedResults } from '../shared/interfaces';
// import { FilterService } from '../core/services/filter.service';
// import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

// import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
// import { CustomValidators } from 'ng2-validation';


// const password = new FormControl('', Validators.required);
// const confirmPassword = new FormControl('', CustomValidators.equalTo(password));
// @Component({
//   selector: 'cm-customers',
//   templateUrl: './customers.component.html'
// })
// export class CustomersComponent implements OnInit {
// public form: FormGroup;
// closeResult:string;
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


//   title: string;
//   filterText: string;
//   customers: ICustomer[] = [];
//   filteredCustomers: ICustomer[] = [];
//   displayMode: DisplayModeEnum;
//   displayModeEnum = DisplayModeEnum;
//   totalRecords = 0;
//   pageSize = 10;

//   constructor(private modalService: NgbModal,private fb: FormBuilder,private dataService: DataService, private filterService: FilterService) { }

//   ngOnInit() {
//     this.title = 'المستخدمين';
//     this.filterText = 'Filter Customers:';
//     this.displayMode = DisplayModeEnum.Card;

//     this.getCustomersPage(1);

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
//   }

//   changeDisplayMode(mode: DisplayModeEnum) {
//       this.displayMode = mode;
//   }

//   pageChanged(page: number) {
//     this.getCustomersPage(page);
//   }

//   getCustomersPage(page: number) {
//     this.dataService.getCustomersPage((page - 1) * this.pageSize, this.pageSize)
//         .subscribe((response: IPagedResults<ICustomer[]>) => {
//           this.customers = this.filteredCustomers = response.results;
//           this.totalRecords = response.totalRecords;
//         },
//         (err: any) => console.log(err),
//         () => console.log('getCustomersPage() retrieved customers for page: ' + page));
//   }

//   filterChanged(data: string) {
//     if (data && this.customers) {
//         data = data.toUpperCase();
//         const props = ['firstName', 'lastName', 'city', 'state.name'];
//         this.filteredCustomers = this.filterService.filter<ICustomer>(this.customers, data, props);
//     } else {
//       this.filteredCustomers = this.customers;
//     }
//   }
// }

// enum DisplayModeEnum {
//   Card = 0,
//   Grid = 1,
//   Map = 2
// }

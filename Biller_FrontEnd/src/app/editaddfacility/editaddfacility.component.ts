import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../core/page-title/page-title.service';
import {fadeInAnimation} from "../core/route-animation/route.animation";

import { trigger, state, style, animate, transition } from '@angular/animations';
import { Http , Headers  } from '@angular/http';
import 'rxjs/add/operator/map';

import { HttpRequestsService } from '../core/http-request-api/http-requests.service';

const password = new FormControl('', Validators.required);
const confirmPassword = new FormControl('', CustomValidators.equalTo(password));

@Component({
    selector: 'ms-bank',
    templateUrl: './editaddfacility.component.html',
    styleUrls: ['../../assets/scss/myStyle.scss'],
    encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },
    animations: [ fadeInAnimation ]
})
export class EditAddFacilityComponent implements OnInit {
  
  @Input() closable = true;
  @Input() visible: boolean;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  close() {
    this.visible = false;
    this.visibleChange.emit(this.visible);
  }
  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    private pageTitleService: PageTitleService, 
    public http: Http,
    private httpRequestsService: HttpRequestsService
  ){

  }
  posts;
  result;
  partyType:string="PARTY_GROUP";
  options;
  ngOnInit() {


    this.form = this.fb.group({
      // partyGroupId: [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
      partyGroupCode: [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
      partyGroupName: [null, Validators.compose([Validators.required])],
      partyGroupType: [null, Validators.compose([Validators.required])],
      partyTaxId: [null, Validators.compose([Validators.required])],
      commericalRegistrationNum: [null, Validators.compose([Validators.required])],
      partyCapital: [null, Validators.compose([Validators.required])],
      partyActivity: [null, Validators.compose([Validators.required])],
      partySize: [null, Validators.compose([Validators.required])],
      monthlyInvoicingRate: [null, Validators.compose([Validators.required])],
      telephoneNumber1: [null, Validators.compose([Validators.required])],
      telephoneNumber2: [null, Validators.compose([Validators.required])],
      postalCode: [null, Validators.compose([Validators.required])],
      mailBox: [null, Validators.compose([Validators.required])],
      companyType: [null, Validators.compose([Validators.required])],
      partyType:[],
      // partyId:[]
    });
  
  }


  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");

    this.http.get('http://localhost:8088/Biller/partyGroup/findPartyGroupById?partyGroupId=0').map(res => res.json()).subscribe((posts) => {
      this.posts = posts;
      for (let item in posts) {
        console.log("from  submit1", posts[item].partyType);
      }
    })
    //////
    var link = 'http://localhost:8090/Biller/party/createParty';
    var body = JSON.stringify(this.form.value);
    
    console.log("data before req " + body);
    this.http.post(link, body,{headers})
    .subscribe(data => {
    this.result = data["_body"];
    console.log('OKOK ' + this.result + ' OKOK');
    }, error => {
    console.log("Oooops!");
    });
    // this.test();

    // this.http.post(link, body, {headers}).map(response => response.json())
    //   .subscribe(
    //   () => { console.log('Success') }
    //   );
  }

  // test(){
  //   console.log("from Fesal " +this.form.value);
  //   var link = 'http://localhost:8090/Biller/party/createParty';
  //   var body = JSON.stringify(this.form.value);
  //   this.httpRequestsService.httpPost(link,this.form.value).subscribe(res =>{
  //     console.log(res);
  //   })
  // }

}




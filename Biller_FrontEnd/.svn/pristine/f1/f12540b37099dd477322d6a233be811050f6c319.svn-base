import { Component, OnInit } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { appConfig } from "../app.config";
import { Http, Headers, RequestOptions } from "@angular/http";
const password = new FormControl('', Validators.required);
const confirmPassword = new FormControl('', CustomValidators.equalTo(password));
@Component({
  selector: 'ms-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['../../assets/scss/myStyle.scss'],
    animations: [
    trigger('dialog', [
      transition('void => *', [
        style({ transform: 'scale3d(.3, .3, .3)' }),
        animate(100)
      ]),
      transition('* => void', [
        animate(100, style({ transform: 'scale3d(.0, .0, .0)' }))
      ])
    ]) ],
})
export class UserprofileComponent implements OnInit {
  showDialog:boolean=false;
  posts;
  result;
  partyType:string="PARTY_GROUP";
  options;
 openModal(){
       (<any>this).display='block'; 
    }

  onCloseHandled(){
       (<any>this).display='none'; 

    }

  constructor(private fb: FormBuilder ,public http: Http) { }
  public form: FormGroup;
  ngOnInit() {
    this.form = this.fb.group({
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
    });
  }



  url2: string = 'http://ssl.gstatic.com/accounts/ui/avatar_2x.png';
  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]);
      console.log(event.target.files[0].name);

      reader.onload = event => {
        this.url2 = event.target["result"];

        let url = appConfig.apiUrl + "userPref/add";
        var headers = new Headers();
        headers.append("Content-Type", "application/x-www-form-urlencoded");
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append(
          "userLoginId",
          sessionStorage.getItem("userLoginId")
        );
        urlSearchParams.append("key", "img");
        urlSearchParams.append("value", this.url2);
        let body = urlSearchParams.toString();
        this.http
          .post(url, body, { headers: headers })
          .map(res => res.json())
          .subscribe(
            data => {
              localStorage.setItem("img", data.value);
              this.url2 = localStorage.getItem("img");
            },
            error => {}
          );
      };
    }

  }


}

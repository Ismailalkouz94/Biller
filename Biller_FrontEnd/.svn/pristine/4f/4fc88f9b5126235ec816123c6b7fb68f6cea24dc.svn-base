import {
  Component,
  OnInit,
  ViewEncapsulation,
  Input,
  Output,
  OnChanges,
  Injectable,
  EventEmitter
} from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";

import { PageTitleService } from "../../core/page-title/page-title.service";

import "rxjs/add/operator/map";
import { Http, Headers, RequestOptions } from "@angular/http";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { ActivatedRoute, Router, Data } from "@angular/router";
import { appConfig } from "../../app.config";
// import {main}
// import { Notifications } from "../../../../notifications/notifications.service";
import { OnDestroy } from "@angular/core";
import { ConfirmationDialog } from "../../confirm-dialog/confirmation-dialog";
import { HttpRequestsService } from "../../core/http-request-api/http-requests.service";
import { Config } from "ngx-easy-table/src/app/ngx-easy-table/model/config";
import { ConfigurationAdvancedService } from "../../shared/service/configuration.service";
@Component({
  selector: "ms-favorite",
  templateUrl: "./favorite.component.html",
  styleUrls: ["../../../assets/scss/myStyle.scss"]
})
export class FavoriteComponent implements OnInit {
  

  public form: FormGroup;
  public partyGroupList: any;
  constructor(
    private fb: FormBuilder,
    private pageTitleService: PageTitleService,
    public http: Http
  ) {
    this.configurationAdvanced = ConfigurationAdvancedService.config;
  }

  configurationAdvanced;
  columns = [
    { key: 'partyId', title: 'رقم الشركة' },
    { key: 'commericalRegistrationNum', title: 'رقم السجل التجاري' },
    { key: 'partyGroupName', title: 'اسم الشركة' },
    { key: 'companyType', title: 'نوع الشركة' },
    { key: 'partyCapital', title: 'رأس مال الشركة' },
    { key: ' ', title: ' ' }
  ];

  columnsFav = [
    { key: 'partyGroupId', title: 'رقم الشركة' },
    { key: 'commericalRegistrationNum', title: 'رقم السجل التجاري' },
    { key: 'partyGroupName', title: 'اسم الشركة' },
    { key: 'companyType', title: 'نوع الشركة' },
    { key: 'partyCapital', title: 'رأس مال الشركة' },
    { key: ' ', title: ' ' }
  ];

  ngOnInit() {
    this.form = this.fb.group({
      companyType: [null],
      description: [null],
      partyId: [null]
    });
    this.getAllPartyGroup();
    this.getFavParties();
    this.getCompanyType();
  }


  favPartyList: any;
  getFavParties() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { partyId: localStorage.getItem("partyId") }
    });
    this.http
      .get(appConfig.apiUrl + "partyGroupFav/findAllFavByParty", options)
      .map(res => res.json())
      .subscribe(data => {
        this.favPartyList = data;
        // return data;
      });
  }
  getAllPartyGroup() {
    // let headers = new Headers();
    // headers.append("Content-Type", "application/json");
    // let options = new RequestOptions({
    //   headers: headers,
    //   params: { partyType:"PARTY_GROUP"  }
    // });
    this.http
      .get(appConfig.apiUrl + "partyGroup/listAllPartyGroup")
      .map(res => res.json())
      .subscribe(data => {
        this.partyGroupList = data.data;
      });
  }

  companyType=[];
  getCompanyType() {
   
   
    this.http
      .get(appConfig.apiUrl + "companyType/findAll")
      .map(res => res.json())
      .subscribe(data => {
        this.companyType = data;
      });
  }


  add(partyFavId){
alert(partyFavId);
  }
}



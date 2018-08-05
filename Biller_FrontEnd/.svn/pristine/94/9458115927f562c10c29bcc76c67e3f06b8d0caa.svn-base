import { Component, OnInit, Injectable, ViewEncapsulation, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';

import { PageTitleService } from '../../core/page-title/page-title.service';
import { fadeInAnimation } from "../../core/route-animation/route.animation";
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Config } from 'ngx-easy-table/src/app/ngx-easy-table/model/config';
import { appConfig } from '../../app.config';
import { ANIMATION_TYPES } from 'ngx-loading';
import { HttpRequestsService } from '../../core/http-request-api/http-requests.service';
import { ConfirmationDialog } from '../../confirm-dialog/confirmation-dialog';
import { MdDialog, MdDialogRef } from '@angular/material';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { ToastsManager } from "ng2-toastr/ng2-toastr";

@Component({
  selector: 'ms-party',
  templateUrl: './party.component.html',
  styleUrls: ['../../../assets/scss/myStyle.scss'],
  animations: [
    trigger('dialog', [
      transition('void => *', [
        style({ transform: 'scale3d(.3, .3, .3)' }),
        animate(100)
      ]),
      transition('* => void', [
        animate(100, style({ transform: 'scale3d(.0, .0, .0)' }))
      ])
    ])],
  encapsulation: ViewEncapsulation.None,
  host: {
    "[@fadeInAnimation]": 'true'
  },


})

export class PartyComponent implements OnInit {
  roleGroup: any;
  secondDialog: boolean;
  firstDialog: boolean;
  dialogRef: MdDialogRef<ConfirmationDialog>;
  partiesList: any;
  public form: FormGroup;
  loading: boolean = false;

  constructor(private fb: FormBuilder,
    private pageTitleService: PageTitleService,
    public http: Http,
    public confirmDialog: MdDialog,
    public toastr: ToastsManager,
  ) {
    this.configurationAdvanced = ConfigurationAdvancedService.config;

  }

  configurationAdvanced;
  columns = [
    // {key:'#',title:'#'},
    { key: 'partyId', title: 'رقم الطرف' },
    { key: 'partyGroup', title: 'اسم الطرف' },
    { key: 'description', title: 'الوصف' },
    { key: ' ', title: ' ' },

  ];

  userName;
  partyId;
  userLoginId;
  roleGroupId;


  showSuccess(message) {
    this.toastr.success(message, "Success!");
  }

  ngOnInit() {
    this.getPartiesData();
    this.getRoleGroup();
  }

  getPartiesData() {
    this.loading = true;
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    // let options = new RequestOptions({ headers: headers, params: { toPartyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'party/findAll').map(res => res.json()).subscribe((data) => {
      this.loading = false;
      this.partiesList = data.data;
    })
  }


  activate(partyId, isActive) {
    this.dialogRef = this.confirmDialog.open(ConfirmationDialog, {
      disableClose: false
    });
    if(isActive == 'Y'){
      this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Activate?"
    }
    else{
      this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to DeActivate?"
    }
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {

        let url = appConfig.apiUrl + 'party/activeParty';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append('partyId', partyId);
        urlSearchParams.append('isActive', isActive);
        let body = urlSearchParams.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe((data) => {
          if (data.isError == false) {
            this.partyId = data.data.partyId;
            if(isActive == 'Y'){
              this.openFirstDialog();
            }
          }
        })

      }
      this.dialogRef = null;
    });
  }

  createDefaultUserLogin() {
    this.dialogRef = this.confirmDialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {

        let url = appConfig.apiUrl + 'userLogin/defaultUserLogin';
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let params ={
          partyId:this.partyId,
          userName: this.userName
        }
        let body = JSON.stringify(params);
        this.http.post(url, body, {headers}).map(res => res.json()).subscribe((data) => {
          console.log(data.data);
          if(data.isError==false){
            this.userLoginId=data.data.userLoginId;
            this.closeFirstDialog();
            this.openSecondDialog();
          }
        })

      }
      this.dialogRef = null;
    });
  }

  getRoleGroup() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers });
    this.http.get(appConfig.apiUrl + 'roleGroup/findAll', options).map(res => res.json()).subscribe((data) => {
      this.roleGroup = data;
    })
  }

  createRoleGroup(){
    this.dialogRef = this.confirmDialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Save?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {

        let url = appConfig.apiUrl + 'userRole/add';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append('userLoginId', this.userLoginId);
        urlSearchParams.append('roleGroupId', this.roleGroupId);
        let body = urlSearchParams.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe((data) => {
          if (data.isError == false) {
            this.partyId = data.data.partyId
            this.closeSecondDialog();
            this.showSuccess("تم العمليه بنجاح")
          }
        })

      }
      this.dialogRef = null;
    });
  }

  openFirstDialog() {
    this.firstDialog = true;
  }

  closeFirstDialog() {
    this.firstDialog = false;
  }

  onCloseHandled() {
    (<any>this).display = 'none';

  }

  openSecondDialog() {
    this.secondDialog = true;
  }

  closeSecondDialog() {
    this.secondDialog = false;
  }


}

@Injectable()
export class ConfigurationAdvancedService {
  public static config: Config = {
    searchEnabled: true,
    headerEnabled: true,
    orderEnabled: true,
    globalSearchEnabled: false,
    paginationEnabled: true,
    exportEnabled: false,
    clickEvent: false,
    selectRow: true,
    selectCol: false,
    selectCell: false,
    rows: 10,
    additionalActions: false,
    serverPagination: false,
    isLoading: false,
    detailsTemplate: false,
    groupRows: false,
    paginationRangeEnabled: true,
    collapseAllRows: false,
    checkboxes: false
  };
}

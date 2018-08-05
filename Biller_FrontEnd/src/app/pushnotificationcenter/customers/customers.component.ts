import {
  Component,
  OnInit,
  ViewEncapsulation,
  Input,
  Output,
  OnChanges,
  EventEmitter,
  Injectable
} from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";

import { PageTitleService } from "../../core/page-title/page-title.service";

import { Http, Headers, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";

import { HttpRequestsService } from "../../core/http-request-api/http-requests.service";
import { appConfig } from "../../app.config";
import { Config } from "ngx-easy-table/src/app/ngx-easy-table/model/config";
import { Router } from "@angular/router";
import { ConfigurationAdvancedService } from "../../shared/service/configuration.service";

// import { TreeviewI18n, DropdownTreeviewComponent } from '../../lib';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ViewContainerRef } from '@angular/core';
import { MdDialogRef, MdDialog } from '@angular/material';
import { ConfirmationDialog } from '../../confirm-dialog/confirmation-dialog';
import { trigger, transition, style, animate } from "@angular/animations";

@Component({
  selector: "ms-customers.",
  templateUrl: "./customers.component.html",
  styleUrls: ["../../../assets/scss/myStyle.scss"],
  animations: [
    trigger("dialog", [
      transition("void => *", [
        style({ transform: "scale3d(.3, .3, .3)" }),
        animate(100)
      ]),
      transition("* => void", [
        animate(100, style({ transform: "scale3d(.0, .0, .0)" }))
      ])
    ])
  ]
})
export class CustomersComponent implements OnInit {
  public form: FormGroup;
  selecteType = "ALL";

  configurationAdvanced;
  columns = [
    { key: "name", title: "اسم المشترك" },
    { key: "nationalNumber", title: " الرقم الوطني" },
    { key: "referenceNumber", title: "رقم الإشتراك" },
    { key: "gender", title: " الجنس" },
    { key: "countryDesc", title: "الدولة" },
   
    { key: "cityDesc", title: "المدينة " },
    { key: "partyType", title: "فئة المشترك" },
    // { key: 'dateSub', title: '  تاريخ الأشتراك' },
    { key: " ", title: " " }
  ];

  constructor(
    private fb: FormBuilder,
    private pageTitleService: PageTitleService,
    public http: Http,
    private router: Router,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public dialogConfirm: MdDialog
  ) {
    this.configurationAdvanced = ConfigurationAdvancedService.config;
    this.toastr.setRootViewContainerRef(vcr);

  }

  countryId;
  cityId;
  cityList;
  showDialog: boolean = false;
  sendToType;
  ngOnInit() {
    this.form = this.fb.group({
      name: [],
      nationalNumber: [],
      countryId:[],
      message:[],
      gender: [],
      cityId: [],
      referenceNumber: []
      // partyId:[]
    });

    this.getCountry();
    this.getPartyName();
  }
  
  userLoginId;
  partyName;
  getPartyName() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { partyId: localStorage.getItem("partyId") }
    });
    this.http
      .get(appConfig.apiUrl + "partyGroup/findByPartyId", options)
      .map(res => res.json())
      .subscribe(data => {
        this.partyName = data.partyGroupName;
      });
  }
  sendToOneUser(userLoginId) {
    this.showDialog = true;
    this.sendToType = "ONEUSER";
    this.userLoginId = userLoginId;
  }

  sendToALL() {
    this.showDialog = true;
    this.sendToType = "ALL";
  }

  userLoginList=[];
  dialogRef: MdDialogRef<ConfirmationDialog>;
  openConfirmationDialog() {
    this.userLoginList = [];
    for (const key in this.personList) {
      this.userLoginList.push(this.personList[key].userLoginId);
    }
    if (this.userLoginList.length === 0) {
      this.showWarning("يرجى اختيار المشتركين");
      return 0;
    }
    let message = this.form.get("message").value;
    console.log(message);
    if (message === "" || message === null) {
      this.showWarning("يرجى ادخال الرسالة");
      return 0;
    }
    this.dialogRef = this.dialogConfirm.open(ConfirmationDialog, {
      disableClose: false
    });

    this.dialogRef.componentInstance.confirmMessage =
      " هل انت متأكد من ارسال الرسالة ؟";
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (this.sendToType === "ALL") {
          this.sendNotifications();
        } else if (this.sendToType === "ONEUSER") {
          this.sendNotificationPerUserLogin(this.userLoginId);
        }
      }
      this.dialogRef = null;
    });
  }

  sendNotifications() {
    this.userLoginList = [];
    for (const key in this.personList) {
      this.userLoginList.push(this.personList[key].userLoginId);
    }

    let message = this.form.get("message").value;
    console.log(message);
    if (message === "" || message === null) {
      this.showWarning("يرجى ادخال الرسالة");
      return 0;
    }

    let url =
      appConfig.apiUrl + "pushNotificationsCenter/sendNotificationsGroups";
    var headers = new Headers();
    headers.append(
      "Content-Type",
      "application/x-www-form-urlencoded;; charset=UTF-8"
    );
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append("userLoginList", this.userLoginList.toString());
    urlSearchParams.append("message", message);
    urlSearchParams.append("partyName", this.partyName);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          this.showSuccess(data.messageAR);
          this.form.get("message").setValue("");
          this.showDialog = false;
        },
        error => {
          this.showError("لم يتم الإرسال بنجاح");
        }
      );
  }

  sendNotificationPerUserLogin(userLoginId) {
    let message = this.form.get("message").value;
    let url =
      appConfig.apiUrl + "pushNotificationsCenter/sendNotificationsSingleUser";
    var headers = new Headers();
    headers.append(
      "Content-Type",
      "application/x-www-form-urlencoded;; charset=UTF-8"
    );
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append("userLoginId", this.userLoginId);
    urlSearchParams.append("message", message);
    urlSearchParams.append("partyName", this.partyName);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          this.showSuccess(data.messageAR);
          this.form.get("message").setValue("");
          this.showDialog = false;
        },
        error => {
          this.showError("لم يتم الإرسال بنجاح");
        }
      );
  }

  countryList = [];
  getCountry() {
    this.http
      .get(appConfig.apiUrl + "country/findAll")
      .map(data => data.json())
      .subscribe(data => {
        this.countryList = data;
        this.countryId = 1;
        this.getCities(this.countryId);
      });
  }

  getCities(countryId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { countryId: countryId }
    });
    this.http
      .get(appConfig.apiUrl + "city/findByCountryId", options)
      .map(data => data.json())
      .subscribe(data => {
        this.cityList = data;
      });
  }
param;
personList;
  search(formData) {
      if (this.selectedCities.length === 0) {
      alert("الرجاء اختيار المدينة");
      return 0;
    }

       var cityIdList = [];
    for (const key in this.selectedCities) {
      cityIdList.push(this.selectedCities[key].cityId);
    }
    var link = "";
    
      link = appConfig.apiUrl + "pushNotificationsCenter/findAllPerson";
  
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");
    var gender;
    if (formData.gender === "ALL") {
      gender = "";
    } else {
      gender = formData.gender;
    }
    this.param = {
      partyId: localStorage.getItem("partyId"),
      name: formData.name,
      nationalNumber:formData.nationalNumber,
      referenceNumber:formData.referenceNumber,
      gender:gender,
      cityIdList: cityIdList,
   
    };

    var body = JSON.stringify(this.param);
    this.http
      .post(link, body, { headers })
      .map(res => res.json())
      .subscribe(
        data => {
          this.personList = data;
        },
        error => {
          this.personList = [];
          // this.showError("خطأ");
          // this.notification.showError("error occured");
        }
      );
  }
  goToLink(link: string): void {
    console.log("Hello");
    this.router.navigateByUrl(link);
  }

  selectedCities = [];
  onSelectedChange_Cities(selectedCities) {
    this.selectedCities = selectedCities;
  }

  dialog = false;
  openUploadDialog() {
    this.dialog = true;
  }

  closeUploadDialog() {
    this.dialog = false;
  }

  onCloseHandled() {
    (<any>this).display = "none";
  }

  
  showSuccess(msg) {
    this.toastr.success(msg, "Success!");
  }
  showError(msg) {
    this.toastr.error(msg, "Oops!");
  }
  showWarning(msg) {
    this.toastr.warning(msg, "Alert!");
  }
}

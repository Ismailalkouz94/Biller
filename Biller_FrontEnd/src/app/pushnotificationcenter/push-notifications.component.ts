import {
  Component,
  OnInit,
  ViewEncapsulation,
  Input,
  Output,
  OnChanges,
  EventEmitter,
  Injectable,
  ViewChild,
  ViewContainerRef
} from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";

import { PageTitleService } from "../core/page-title/page-title.service";

import { Http, Headers, RequestOptions } from "@angular/http";
import "rxjs/add/operator/map";
import { Router } from "@angular/router";

import { HttpRequestsService } from "../core/http-request-api/http-requests.service";
import { appConfig } from "../app.config";
import { Config } from "ngx-easy-table/src/app/ngx-easy-table/model/config";
import { TreeviewI18n, DropdownTreeviewComponent } from "../lib";
// import { City, CityService } from '../lib/city.service';
// import { CityTreeviewI18n } from '../lib/city-treeview-i18n';
import {
  trigger,
  state,
  style,
  animate,
  transition
} from "@angular/animations";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { forEach } from "@angular/router/src/utils/collection";
import { ConfigurationAdvancedService } from "../shared/service/configuration.service";
import { NotificationsService } from "../notifications/notifications.service";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { MdDialogRef, MdDialog } from "@angular/material";
import { ConfirmationDialog } from "../confirm-dialog/confirmation-dialog";

@Component({
  selector: "ms-push-notifications.",
  templateUrl: "./push-notifications.component.html",
  styleUrls: ["../../assets/scss/myStyle.scss"],
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
export class PushNotificationsComponent implements OnInit {
  dropdownTreeviewComponent: any;
  public formBiller: FormGroup;
  public formPerson: FormGroup;
  isOption: boolean;
  showDialog: boolean = false;
  configurationAdvanced;
  columns = [
    { key: "companyId", title: "رقم المنشاه" },
    { key: "partyGroupName", title: "اسم المنشأه" },
    { key: "commericalRegistrationNum", title: " الرقم الوطني" },
    { key: "partySize", title: "حجم المنشاه" },
    { key: "country", title: "الدولة" },
    { key: "city", title: "المدينة " },
    { key: "partyGroupType", title: " نوع المنشأة" },
    { key: " ", title: " " }
  ];

  constructor(
    // private service: CityService,
    private fb: FormBuilder,
    private pageTitleService: PageTitleService,
    public http: Http,
    private router: Router,
    private notifications: NotificationsService,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public dialogConfirm: MdDialog
  ) {
    this.toastr.setRootViewContainerRef(vcr);
    this.configurationAdvanced = ConfigurationAdvancedService.config;
  }

  countryId;
  cityId;
  userLoginList = [];
  cityList = [];
  selecteItem = "ALL";
  selecteType = "ALL";
  contrySelected;
  ngOnInit() {
    this.formBiller = this.fb.group({
      companyId: [],
      partyGroupName: [],
      countryId: [],
      commericalRegistrationNum: [],
      partySizeId: [],
      subscriptionType: [null, Validators.required],
      message: []
      // partyId:[]
    });
    this.getPartyName();
    this.getCountry();
    this.getCompanyType();
    this.getPartySize();
  }

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

  partySizeList;
  getPartySize() {
    this.http
      .get(appConfig.apiUrl + "partySize/findAll")
      .map(data => data.json())
      .subscribe(data => {
        this.partySizeList = data;
      });
  }

  companyTypeList = [];
  getCompanyType() {
    this.http
      .get(appConfig.apiUrl + "companyType/findAll")
      .map(data => data.json())
      .subscribe(data => {
        this.companyTypeList = data;
      });
  }

  param;
  subscripersList;
  search(formData) {
    if (this.selectedTypies.length === 0) {
      alert("الرجاء اختيار نوع الشركة");
      return 0;
    }
    if (this.selectedCities.length === 0) {
      alert("الرجاء اختيار المدينة");
      return 0;
    }

    var companyTypeList = [];
    for (const key in this.selectedTypies) {
      companyTypeList.push(this.selectedTypies[key].id);
    }
    var cityIdList = [];

    for (const key in this.selectedCities) {
      cityIdList.push(this.selectedCities[key].cityId);
    }
    if (formData.subscriptionType === null) {
      this.showWarning("الرجاء اختيار نوع المشتركين");

      return 0;
    }
    var link = "";
    if (formData.subscriptionType === "ALL") {
      link = appConfig.apiUrl + "pushNotificationsCenter/findAllSubscribers";
    } else if (formData.subscriptionType === "YES") {
      link = appConfig.apiUrl + "pushNotificationsCenter/findSubscribers";
    } else if (formData.subscriptionType === "NO") {
      link = appConfig.apiUrl + "pushNotificationsCenter/findNonSubscribers";
    }
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");
    var partySize;
    if (formData.partySizeId === "ALL") {
      partySize = "";
    } else {
      partySize = formData.partySizeId;
    }
    this.param = {
      partyId: localStorage.getItem("partyId"),
      commericalRegistrationNum: formData.commericalRegistrationNum,
      partyGroupName: formData.partyGroupName,
      companyTypeList: companyTypeList,
      cityIdList: cityIdList,
      partySizeId: partySize,
      companyId: formData.companyId
    };

    var body = JSON.stringify(this.param);
    this.http
      .post(link, body, { headers })
      .map(res => res.json())
      .subscribe(
        data => {
          this.partyGroupList = data;
        },
        error => {
          this.partyGroupList = [];
          // this.showError("خطأ");
          // this.notification.showError("error occured");
        }
      );
  }
  dialogRef: MdDialogRef<ConfirmationDialog>;

  openConfirmationDialog() {
    this.userLoginList = [];
    for (const key in this.partyGroupList) {
      this.userLoginList.push(this.partyGroupList[key].userLoginId);
    }
    if (this.userLoginList.length === 0) {
      this.showWarning("يرجى اختيار المشتركين");
      return 0;
    }
    let message = this.formBiller.get("message").value;
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

  sendToType = "";
  sendNotificationPerUserLogin(userLoginId) {
    let message = this.formBiller.get("message").value;
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
          this.formBiller.get("message").setValue("");
          this.showDialog = false;
        },
        error => {
          this.showError("لم يتم الإرسال بنجاح");
        }
      );
  }
  userLoginId;
  partyName;
  sendToOneUser(userLoginId) {
    this.showDialog = true;
    this.sendToType = "ONEUSER";
    this.userLoginId = userLoginId;
  }

  sendToALL() {
    this.showDialog = true;
    this.sendToType = "ALL";
  }

  sendNotifications() {
    this.userLoginList = [];
    for (const key in this.partyGroupList) {
      this.userLoginList.push(this.partyGroupList[key].userLoginId);
    }

    let message = this.formBiller.get("message").value;
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
          this.formBiller.get("message").setValue("");
          this.showDialog = false;
        },
        error => {
          this.showError("لم يتم الإرسال بنجاح");
        }
      );
  }

  partyGroupList;
  goToLink(link: string): void {
    this.router.navigateByUrl(link);
  }

  // @ViewChild(DropdownTreeviewComponent) dropdownTreeviewComponent: DropdownTreeviewComponent;
  // cities: City[];
  // selectedCities: City[];
  // unselectedCities: City[];
  // selectedCompanyType:City[];

  selectedCities = [];
  selectedTypies = [];
  onSelectedChange_companyType(selectedType) {
    this.selectedTypies = selectedType;
  }

  onSelectedChange_Cities(selectedCities) {
    this.selectedCities = selectedCities;
    for (let row of this.selectedCities) {
    }
    // const uncheckedItems = this.dropdownTreeviewComponent.treeviewComponent.selection.uncheckedItems;
    // this.unselectedCities = uncheckedItems.map(item => item.value);
  }
  dialog = false;
  openUploadDialog() {
    this.dialog = true;
  }

  closeUploadDialog() {
    this.dialog = false;
    this.formBiller.get("message").setValue("");
  }
  openModal() {
    (<any>this).display = "block";
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

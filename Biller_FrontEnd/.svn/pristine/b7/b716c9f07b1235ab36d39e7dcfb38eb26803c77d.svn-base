import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { FileUploader } from "ng2-file-upload/ng2-file-upload";
import { Http, Headers, RequestOptions } from "@angular/http";
import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { appConfig } from "../../../app.config";
import { NotificationsService } from "../../../notifications/notifications.service";

import "rxjs/add/operator/map";
import { Router } from "@angular/router";
import { ConfigurationAdvancedService } from "../../../shared/service/configuration.service";
@Component({
  selector: "ms-invoice",
  templateUrl: "./invoiceReceived.component.html",
  styleUrls: ["../../../../assets/scss/myStyle.scss"]
})
export class InvoiceReceivedComponent implements OnInit {
  invoiceDate: any;
  params: any;
  favPartyList: any;
  public invoiceList;
  public data;
  updateList;
  invoiceTypelist;
  invoiceStatuslist;
  public filterQuery = "";
  public rowsOnPage = 5;
  public sortBy = "invoiceDate";
  public sortOrder = "asc";

  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public http: Http,
    public router: Router
  ) {
    this.configurationAdvanced = ConfigurationAdvancedService.config;
    

  }

  ngOnInit() {
    this.form = this.fb.group({
		toName: [],
		description: [],
		statusId: [],
		fromPartyId:[],
		orderId: [],
		paidNumber:[],
		invoiceDate: [null, Validators.compose([CustomValidators.date])],
		dueDate: [ null,Validators.compose([CustomValidators.date])],
		invoiceId: [],
		paidDate: [ null,Validators.compose([CustomValidators.date])]
	  });
    this.getInvoiceData();
    this.getInvoiceType();
    this.getInvoiceStatus();
    this.getSentFrom();
  }
  getInvoiceData() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");

    let options = new RequestOptions({
      headers: headers,
      params: { toPartyId: localStorage.getItem("partyId") }
    });
    this.http
      .get(appConfig.apiUrl + "invoice/findByToPartyId", options)
      .map(res => res.json())
      .subscribe(invoiceList => {
        this.invoiceList = invoiceList.data;
      });
  }


  configurationAdvanced;
  columns = [
  
    { key: 'invoiceId', title: 'رقم الفاتورة' },
    { key: 'paidNumber', title: 'رقم الدفع' },
    { key: 'description', title: 'الوصف' },
    { key: 'status', title: 'الحالة' },
    { key: 'invoiceDate', title: 'التاريخ' },
    { key: 'dueDate', title: 'تاريخ الاستحقاق' },
    { key: 'paidDate', title: 'تاريخ الدفع' },
    { key: 'toParty', title: ' من مفوتر' },
   
    { key: 'orderId', title: ' الطلب' },
    { key: ' total', title: ' مجموع السلع' },
    { key: ' ', title: ' ' },
   
  ];
  public sortByWordLength = (a: any) => {
    return a.invoiceId.length;
  };

  findByCriteria(formData) {
    // alert(formData.invoiceId);

    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    console.log(formData);
    if (formData.invoiceDate != null) {
      this.invoiceDate = new Date(formData.invoiceDate);
    }
    this.params = {
      fromPartyId: formData.fromPartyId,
      invoiceId: formData.invoiceId,
      description: formData.description,
      toName: formData.toName,
      invoiceDate: this.invoiceDate,
      dueDate: formData.dueDate,
      toPartyId: localStorage.getItem("partyId"),
      paidDate: formData.paidDate,
      orderId: formData.orderId,
      statusId: formData.statusId,
      paidNumber: formData.paidNumber
    };
    for (var key in this.params) {
      if (this.params[key] === null) {
        delete this.params[key];
      }
    }
    let options = new RequestOptions({
      headers: headers,
      params: this.params
    });

    this.http
      .get(appConfig.apiUrl + "invoice/findByCriteria", options)
      .map(res => res.json())
      .subscribe(invoiceList => {
        this.invoiceList = invoiceList;
      });
  }

  // getFavParties() {
  //   let headers = new Headers();
  //   headers.append("Content-Type", "application/json");
  //   let options = new RequestOptions({
  //     headers: headers,
  //     params: { partyId: sessionStorage.getItem("partyId") }
  //   });
  //   this.http
  //     .get(appConfig.apiUrl + "partyGroupFav/listAllFavByParty", options)
  //     .map(res => res.json())
  //     .subscribe(data => {
  //       this.favPartyList = data;
  //       return data;
  //     });
  // }

  public show: boolean = false;
  public buttonName: any = "بحث متقدم";
  public arraw: any = "arrow_drop_down";

  toggle() {
    this.show = !this.show;

    // CHANGE THE NAME OF THE BUTTON.
    if (this.show) {
      this.buttonName = "اخفاء";
      this.arraw = "arrow_drop_up";
    } else {
      this.buttonName = "بحث متقدم";
      this.arraw = "arrow_drop_down";
    }
  }

param;
 

showTheBill(layout){
  let popupWin = window.open('تقرير تفصيلي', '_blank');
  

  popupWin.document.open();
  popupWin.document.write(layout);
  popupWin.document.close();
}


invoiceDetailsReport(invoice) {


  var link = appConfig.apiUrl + "report/reportSwitch";
  let headers = new Headers();
  headers.append("Content-Type", "application/json;charset=UTF-8");
  if(invoice.invoiceTypeId.globalItemId ==="INVOICE_DETAILS"){
    this.param = {
      "partyId":invoice.fromParty.partyId,
      "templateId":"CUSTOM",
      "invoiceId":invoice.invoiceId	
      }
  } else if(invoice.invoiceTypeId.globalItemId ==="INVOICE_ITEM"){
    this.param = {
      "partyId":invoice.fromParty.partyId,
      "templateId":"INVOICE_DETAILS",
      "invoiceId":invoice.invoiceId	
      }
  }
  

  var body = JSON.stringify(this.param);
  this.http
    .post(link, body, { headers })
    .map(res => res.json())
    .subscribe(
      invoiceList => {
        this.showTheBill(invoiceList.data);
// this.hiddenBtn="true";
// this.getInvoiceData();
      },
      error => {
       
        // this.showError("خطأ");
        // this.notification.showError("error occured");
      }
    ); 

  }


  delete(invoiceId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { id: invoiceId }
    });

    this.http.delete(appConfig.apiUrl + "invoice/delete", options).subscribe(
      data => {
        // this.notification.showSuccess()
        this.getInvoiceData();
      },
      error => {
        // this.showError()
        console.log("Oooops!");
      }
    );
  }

  update(invoiceId) {
    localStorage.setItem("invoiceId", invoiceId);
    this.router.navigate(["/invoice/view-invoice"]);
  }
  sentFromList;
  getSentFrom() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { toPartyId: localStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'invoice/findBySentFrom', options).map(res => res.json()).subscribe((data) => {
      this.sentFromList = data.data;

    });
  } 

  totalPrice(invoiceItem) {
    let total = 0;
    for (let item in invoiceItem) {
      total += invoiceItem[item].unitPrice*invoiceItem[item].quantity;
    }
   console.log(total);
    return total;
  }
  getInvoiceType() {
    this.http
      .get(
        appConfig.apiUrl +
          "globalItem/findByCriteria?globalTypeId=INVOICE_TYPE_ID"
      )
      .map(res => res.json())
      .subscribe(invoiceTypelist => {
        this.invoiceTypelist = invoiceTypelist;
      });
  }

  getInvoiceStatus() {
    this.http
      .get(
        appConfig.apiUrl +
          "globalItem/findByCriteria?globalTypeId=INVOICE_STATUS"
      )
      .map(res => res.json())
      .subscribe(invoiceStatuslist => {
        this.invoiceStatuslist = invoiceStatuslist;
      });
  }

  public toInt(num: string) {
    return +num;
  }
}

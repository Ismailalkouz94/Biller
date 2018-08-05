import { Component, OnInit, ViewEncapsulation, Injectable, ViewContainerRef } from "@angular/core";
import { FormBuilder, FormGroup, Validators, FormControl } from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { Http, Headers, RequestOptions } from '@angular/http';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { appConfig } from '../../../../app.config';
import { Router } from '@angular/router';
import { MdDialog, MdDialogRef } from '@angular/material';
import { ConfirmationDialog } from '../../../../confirm-dialog/confirmation-dialog';
import { Config } from 'ngx-easy-table/src/app/ngx-easy-table/model/config';

@Component({
  selector: "ms-orderReceived",
  templateUrl: "./orderReceived.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"],
})
export class OrderReceivedMainComponent implements OnInit {
  orderStatuslist: Array<Object> = [];
  param;
  public form: FormGroup;
  dialogRef: MdDialogRef<ConfirmationDialog>;

  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public http: Http,
    private router: Router,
    public dialog: MdDialog

  ) {
    this.toastr.setRootViewContainerRef(vcr);
    this.configurationAdvanced = ConfigurationAdvancedService.config;

  }

  ordersList;
  fromPartyList;

  orderDate: Date;
  params;

  configurationAdvanced;
  columns = [
    // {key:'#',title:'#'},
    { key: 'orderId', title: 'رقم الطلب' },
    { key: 'name', title: 'اسم الطلب' },
    { key: 'description', title: 'الوصف' },
    { key: 'globalItem', title: 'حالة الطلب' },
    { key: 'toParty', title: 'من' },
    { key: 'orderDate', title: 'التاريخ' },
    { key: ' total', title: ' مجموع السلع' },
    { key: ' ', title: ' ' },
  ];


  showSuccess() {
    this.toastr.success("You are awesome!", "Success!");
  }
  showError() {
    this.toastr.error("This is not good!", "Oops!");
  }
  showErrorWithMessage(message) {
    this.toastr.error(message);
  }
  showWarning() {
    this.toastr.warning("You are being warned.", "Alert!");
  }
  showInfo() {
    this.toastr.info("Just some information for you.");
  }

  showCustom() {
    this.toastr.custom(
      '<span style="color: red">Message in red.</span>',
      null,
      { enableHTML: true }
    );
  }


  ngOnInit() {
    this.form = this.fb.group({
      // orderCode: [null],
      name: [],
      description: [],
      globalItemId: [],
      orderDate: [],
      fromPartyId: [],
      toPartyId: [],
      orderId: []
    });

    this.getOrdersData();
    this.getfromParties();
    this.getOrderStatus();
  }



  getOrdersData() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { toPartyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'orders/findByToPartyId', options).map(res => res.json()).subscribe((data) => {
      this.ordersList = data.data;
    })
  }

  getOrderStatus() {
    this.http.get(appConfig.apiUrl+"globalItem/findByCriteria?globalTypeId=ORDER_STATUS"
      ).map(res => res.json()).subscribe(orderStatuslist => {
        for (var item in orderStatuslist) {
          if (orderStatuslist[item].globalItemId != "CREATED") {
             this.orderStatuslist.push(orderStatuslist[item]);
          }
        }
      });
  }

  totalPrice(orderItems) {
    let total = 0;
    for (let item in orderItems) {
      total += orderItems[item].unitPrice * orderItems[item].quantity;
    }
    return total;
  }


  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: this.form.value });


  }


  view(order) {
    sessionStorage.setItem("orderId", order.orderId);
    localStorage.setItem("orderStatus", order.globalItem.globalItemId);
    this.router.navigate(['/order/order-orderReceived']);
  }

  edit(orderId) {
    sessionStorage.setItem("orderId", orderId);
    this.router.navigate(['/order/add-orderItemReceived']);
  }



  getfromParties() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { toPartyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'orders/findFrom', options).map(res => res.json()).subscribe((data) => {
      this.fromPartyList = data.data;
    })
  }


  findByCriteria(formData) {
    // alert(formData.invoiceId);

    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    console.log(formData);
    if (formData.orderDate != null) {
      this.orderDate = new Date(formData.orderDate);
    }
    this.params = {
      fromPartyId: formData.toPartyId,
      orderId: formData.orderId,
      description: formData.description,
      name: formData.name,
      orderDate: this.orderDate,
      toPartyId: sessionStorage.getItem('partyId'),
      globalItemId: formData.globalItemId,

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

    this.http.get(appConfig.apiUrl + "orders/findByCriteria", options).map(res => res.json())
      .subscribe(ordersList => {
        this.ordersList = ordersList;
      });
  }



  // getPartyGroupDetails(partyId) {
  //   let headers = new Headers();
  //   headers.append("Content-Type", "application/json");
  //   let options = new RequestOptions({ headers: headers, params: { partyId: partyId } });
  //   this.http.get(appConfig.apiUrl + 'partyGroup/findByPartyId', options).map(res => res.json()).subscribe((data) => {
  //     return data.tradeName;
  //   })
  // }

  changeToInitialReceived(orderId) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Initial Receive?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let url = appConfig.apiUrl + 'orders/changeStatus';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append('orderId', orderId);
        urlSearchParams.append('status', 'INITIAL');
        let body = urlSearchParams.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
          this.showSuccess();
          this.getOrdersData();
        }, error => {
          this.showError();
        })
      }
      this.dialogRef = null;
    });

  }

  changeToFinalConfirm(orderId) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Confirm?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let url = appConfig.apiUrl + 'orders/changeStatus';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append('orderId', orderId);
        urlSearchParams.append('status', 'CONFIRMATION');
        let body = urlSearchParams.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
          this.showSuccess();
          this.getOrdersData();
        }, error => {
          this.showError();
        })
      }
      this.dialogRef = null;
    });
  }

  orderTotalReport(orderId) {
    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");

    this.param = {
      "partyId": localStorage.getItem("partyId"),
      "templateId": "ORDER_TOTAL",
      "orderId": orderId
    }
    var body = JSON.stringify(this.param);
    this.http.post(link, body, { headers }).map(res => res.json()).subscribe(data => {
      this.showTheBill(data.data);
    },
      error => {
        this.showError();
      }
    );
  }


  orderDetailReport(orderId) {
    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");
    this.param = {
      "partyId": localStorage.getItem("partyId"),
      "templateId": "ORDER_DETAILS",
      "orderId": orderId
    }
    var body = JSON.stringify(this.param);
    this.http.post(link, body, { headers }).map(res => res.json()).subscribe(data => {
      this.showTheBill(data.data);
    },
      error => {
        this.showError();
      }
    );

  }

  showTheBill(layout) {
    let popupWin = window.open('تقرير تفصيلي', '_blank');
    popupWin.document.open();
    popupWin.document.write(layout);
    popupWin.document.close();
  }


  deliveryNoteReport(orderId) {
    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");

    this.param = {
      "partyId": localStorage.getItem("partyId"),
      "templateId": "DELIVARY_NOTE",
      "orderId": orderId
    }
    var body = JSON.stringify(this.param);
    this.http.post(link, body, { headers }).map(res => res.json()).subscribe(data => {
      this.showTheBill(data.data);
    },
      error => {
        this.showError();
      }
    );
  }


  invoice(orderId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { orderId: orderId } });
    this.http.get(appConfig.apiUrl + 'invoice/findByOrderId', options).map(res => res.json()).subscribe((data) => {
      if (data.data != null) {
        localStorage.setItem("invoiceId", data.data.invoiceId);
        this.invoiceDetailsReport(data.data.invoiceId);
      } else {
        this.showErrorWithMessage("NO Information");
      }
    })
  }


  invoiceDetailsReport(invoiceId) {
    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");
    this.param = {
      "partyId": localStorage.getItem("partyId"),
      "templateId": "INVOICE_DETAILS",
      "invoiceId": invoiceId
    }
    var body = JSON.stringify(this.param);
    this.http.post(link, body, { headers }).map(res => res.json()).subscribe(data => {
      this.showTheBill(data.data);
    },
      error => {
        this.showError();
      }
    );
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
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
  selector: "ms-orderSent",
  templateUrl: "./orderSent.component.html",
  styleUrls: ["../../../../../assets/scss/myStyle.scss"],
  
})
export class OrderSentMainComponent implements OnInit {
  orderStatuslist: any;
  param;
  toPartyList: any;
  orderDate: Date;
  params;
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
  favPartyList;


  configurationAdvanced;
  columns = [
    // {key:'#',title:'#'},
    { key: 'orderId', title: 'رقم الطلب' },
    { key: 'name', title: 'اسم الطلب' },
    { key: 'description', title: 'الوصف' },
    { key: 'globalItem', title: 'حالة الطلب' },
    { key: 'toParty', title: 'ألى' },
    { key: 'orderDate', title: 'التاريخ' },
    { key: ' total', title: ' مجموع السلع' },
    { key: ' ', title: ' ' },
  ];


  showSuccess() {
    this.toastr.success("You are awesome!", "Success!");
  }
  showSuccessWithMessage(message) {
    this.toastr.success(message, "Success!");
  }

  showError() {
    this.toastr.error("This is not good!", "Oops!");
  }
  showErrorWithMessage(message) {
    this.toastr.error(message, "Oops!");
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
    this.getToParties();

    this.form = this.fb.group({
      // orderCode: [null],
      name: [],
      description: [],
      globalItemId: [],
      orderDate: [],
      toPartyId: [],
      fromPartyId: [],
      orderId: []
    });

    this.getOrdersData();
    this.getOrderStatus();
  }

  getOrdersData() {
      let headers = new Headers();
      headers.append("Content-Type", "application/json");
      let options = new RequestOptions({ headers: headers, params: { fromPartyId: sessionStorage.getItem("partyId") } });
      this.http.get(appConfig.apiUrl + 'orders/findByFromPartyId', options).map(res => res.json()).subscribe((data) => {
        this.ordersList = data.data;
      })
  }

  openConfirmationDialog(orderId) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to delete?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.delete(orderId);
      }
      this.dialogRef = null;
    });
  }

  delete(orderId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: orderId } });

    this.http.delete(appConfig.apiUrl + 'orders/delete', options).map(res => res.json()).subscribe(data => {
      if (data.isError == false) {
        this.showSuccessWithMessage(data.messageAR);
        this.getOrdersData();
      } else {
        this.showErrorWithMessage(data.messageEN);
      }
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }

  getToParties() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { fromPartyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'orders/findTo', options).map(res => res.json()).subscribe((data) => {
      this.toPartyList = data.data;
    })
  }
  view(order) {
    sessionStorage.setItem("orderId", order.orderId);
    localStorage.setItem("orderStatus", order.globalItem.globalItemId);
    this.router.navigate(['/order/update-order']);
  }
  edit(order) {
    sessionStorage.setItem("orderId", order.orderId);
    localStorage.setItem("orderStatus", order.globalItem.globalItemId);
    this.router.navigate(['/order/add-orderItemSent']);
  }

  totalPrice(orderItems) {
    let total = 0;
    for (let item in orderItems) {
      total += orderItems[item].unitPrice * orderItems[item].quantity;
    }
    return total;
  }

  findByCriteria(formData) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    console.log(formData);
    if (formData.orderDate != null) {
      this.orderDate = new Date(formData.orderDate);
    }
    this.params = {
      fromPartyId: sessionStorage.getItem('partyId'),
      orderId: formData.orderId,
      description: formData.description,
      name: formData.name,
      orderDate: this.orderDate,
      toPartyId: formData.toPartyId,
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

  getOrderStatus() {
    this.http.get(appConfig.apiUrl+"globalItem/findByCriteria?globalTypeId=ORDER_STATUS"
      ).map(res => res.json()).subscribe(orderStatuslist => {
        this.orderStatuslist = orderStatuslist;
      });
  }

  changeToSent(order) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to send?";
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (order.orderItems[0] != null) {
          let url = appConfig.apiUrl + 'orders/changeStatus';
          var headers = new Headers();
          headers.append('Content-Type', 'application/x-www-form-urlencoded');
          let urlSearchParams = new URLSearchParams();
          urlSearchParams.append('orderId', order.orderId);
          urlSearchParams.append('status', 'SENT');
          let body = urlSearchParams.toString()
          this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
            this.showSuccessWithMessage("تمت العمليه بنجاح");
            this.getOrdersData();
          }, error => {
            this.showError();
          })
        } else {
          this.showErrorWithMessage("this Order Not Contains Items");
        }
      }
      this.dialogRef = null;
    });
  }

  changeToCancel(orderId) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Cancel?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let url = appConfig.apiUrl + 'orders/changeStatus';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append('orderId', orderId);
        urlSearchParams.append('status', 'CANCELED');
        let body = urlSearchParams.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
          this.showSuccessWithMessage("تمت العمليه بنجاح");
          this.getOrdersData();
        }, error => {
          this.showError();
        })
      }
      this.dialogRef = null;
    });
  }

  changeToCompleteConfirm(orderId) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Complete?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let url = appConfig.apiUrl + 'orders/changeStatus';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append('orderId', orderId);
        urlSearchParams.append('status', 'COMPLETED');
        let body = urlSearchParams.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
          this.showSuccessWithMessage("تمت العمليه بنجاح");
          this.getOrdersData();
        }, error => {
          this.showError();
        })
      }
      this.dialogRef = null;
    });
  }

  changeToUpdate(orderId) {
    let url = appConfig.apiUrl + 'orders/changeStatus';
    var headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('orderId', orderId);
    urlSearchParams.append('status', 'B_UPDATED');
    let body = urlSearchParams.toString()
    this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
      this.showSuccessWithMessage("تمت العمليه بنجاح");
      this.getOrdersData();
    }, error => {
      this.showError();
    })
  }

  createInvoice(orderId) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to Create Invoice?"
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let url = appConfig.apiUrl + 'orders/addOrderInvoice';
        var headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append('orderId', orderId);
        let body = urlSearchParams.toString()
        this.http.post(url, body, { headers: headers }).map(res => res.json()).subscribe(data => {
          if (data.isError == false) {
            this.showSuccessWithMessage(data.messageAR + " " + data.data.invoiceId);
            this.getOrdersData();
          } else {
            this.showErrorWithMessage(data.messageEn);
          }
        }, error => {
          this.showError();
        })
      }
      this.dialogRef = null;
    });
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
        this.createInvoice(orderId);
      }
    })
  }

  showTheBill(layout) {
    let popupWin = window.open('تقرير تفصيلي', '_blank');
    popupWin.document.open();
    popupWin.document.write(layout);
    popupWin.document.close();
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

  invoiceDetailsReport(invoice) {
    var link = appConfig.apiUrl + "report/reportSwitch";
    let headers = new Headers();
    headers.append("Content-Type", "application/json;charset=UTF-8");
    this.param = {
      "partyId": localStorage.getItem("partyId"),
      "templateId": "INVOICE_ITEM",
      "invoiceId": invoice[0].invoiceId
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
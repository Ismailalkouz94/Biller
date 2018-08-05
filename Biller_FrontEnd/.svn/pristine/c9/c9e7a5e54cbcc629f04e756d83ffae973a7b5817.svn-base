import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { PageTitleService } from '../../core/page-title/page-title.service';
import {fadeInAnimation} from "../../core/route-animation/route.animation";
import { messages, users, single, multi, dashboardbar, map1, map2, map3, map4 } from './../dashboard.data';
import { Http, Headers, RequestOptions } from "@angular/http";
import { appConfig } from "../../app.config";
@Component({
  selector: 'ms-dashboard',
  templateUrl:'./dashboard-component.html',
  styleUrls: ['./dashboard-component.scss'],
  encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },
    animations: [ fadeInAnimation ]
})
export class DashboardComponent implements OnInit  {

  lat: number = 50.937531;
  lng: number = 6.960278600000038;

  public messages: any[];
  public users: any[];
  public single: any[];
  public multi: any[];
  public dashboardbar: any[];
  public map1: any[];
  public map2: any[];
  public map3: any[];
  public map4: any[];
  constructor( private pageTitleService: PageTitleService,public http: Http) {
    this.messages = messages;
    this.users = users;
    this.single = single;
    this.multi = multi;
    this.dashboardbar = dashboardbar;
    this.map1 = map1;
    this.map2 = map2;
    this.map3 = map3;
    this.map4 = map4;
  }
  ngOnInit() {
    this.pageTitleService.setTitle("الرئيسية");
    this.getDashboardData();
  }

  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Country';
  showYAxisLabel = true;
  showLabels = true;
  yAxisLabel = 'Population';
  doughnut = false;

  colorScheme = {
    domain: ['#32936f', '#e83f6f', '#ffbf00 ', '#2274a5']
  };
  
  colorScheme1 = {
    domain: ['#32936f']
  };
  colorScheme2 = {
    domain: ['#e83f6f']
  };
  colorScheme3 = {
    domain: ['#ffbf00 ']
  };
  colorScheme4 = {
    domain: ['#2274a5']
  };
  
  onSelect(event) {
    console.log(event);
  }
data;
invoicePaid;
invoiceNotPaid;
ordersBilling;
ordersNotBilling;
invoiceDashboard ;
ordersDashboard;
getDashboardData(){

    let headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('partyId', localStorage.getItem("partyId"));

    let body = urlSearchParams.toString();
    var link = appConfig.apiUrl + 'report/reportDashboard';
    this.http.post(link,body, { headers }).map(res => res.json()).subscribe((data) => {
      this.data = data.data;
     this.ordersNotBilling = this.data.ordersNotBilling;
     this.ordersBilling =this.data.ordersBilling;
     this.invoiceNotPaid =this.data.invoiceNotPaid;
     this.invoicePaid =this.data.invoicePaid;
     this.invoiceDashboard =this.data.invoiceDashboard;
     this.ordersDashboard = this.data.ordersDashboard;
    },
      error => {
      }
    );
  }
  
 
}
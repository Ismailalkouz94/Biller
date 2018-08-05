import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import {  OnInit, ViewEncapsulation } from "@angular/core";

import { Http, Headers, RequestOptions } from "@angular/http";
import { appConfig } from "./../../app.config";
@Injectable()
export class UtilService {
  favPartyList;
  constructor(public http:Http) { }

  getFavParties() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { partyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'partyGroupFav/listAllFavByParty', options).map(res => res.json()).subscribe((data) => {
	  this.favPartyList = data;
	  return data;
	});

  }

}
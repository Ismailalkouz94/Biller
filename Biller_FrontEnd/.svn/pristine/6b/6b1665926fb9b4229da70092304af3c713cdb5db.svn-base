import { Injectable } from '@angular/core';
import { appConfig } from '../../../app.config';
import { Http } from '@angular/http';

export interface ChildrenItems {
  state: string;
  name: string;
  type?: string;
}

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  children?: ChildrenItems[];
}

const MENUITEMS = [];


@Injectable()
export class MenuItems {

  constructor(public http:Http) { 
    console.log('Data service connected...');
  }
  getPosts(){
    return this.http.get(appConfig.apiUrl + 'menus/menusTree')
      .map(res => res.json());
  }

}
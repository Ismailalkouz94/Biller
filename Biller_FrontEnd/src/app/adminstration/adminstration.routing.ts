import { Routes } from '@angular/router';


import { BillersComponent } from './billers/billers.component';
import { PartyComponent } from './party/party.component';


export const AdminstrationRoutes: Routes = [{
  path: '',
  redirectTo: 'billers',
  pathMatch: 'full',
},{
    path: 'billers',
    component: BillersComponent
  },
   {
    path: 'party',
    component: PartyComponent
  }];

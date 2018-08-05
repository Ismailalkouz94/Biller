import { NgModule } from '@angular/core';
import { AccordionModule} from 'ngx-bootstrap';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';
import { TabsModule  } from 'ngx-bootstrap';
import { TreeviewModule } from '../lib';
// import {NgxPaginationModule} from 'ngx-pagination';
import {Tree2Module} from '../shared/tree/tree';

import {OrderManagementRoutes } from './order-management.routing';

import { AddOrderComponent } from './order-sent/order/add/add-order.component';
import { UpdateOrderComponent } from './order-sent/order/update/update-order.component';
import { OrderSentMainComponent } from './order-sent/order/search/orderSent.component';

import { AddOrderItemSentComponent } from './order-sent/order-item/add/add-order-item.component';

import {AddOrderItemReceivedComponent} from './order-received/order-item/add/add-order-item.component'
import { UpdateOrderReceivedComponent } from './order-received/order/update/update-orderReceived.component';
import { SharedModule } from '../SharedModule';


import {OrderReceivedMainComponent} from './order-received/order/search/orderReceived.component'
import {DataTableModule} from "angular2-datatable";
@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    FormsModule,
    AccordionModule.forRoot(),
    ReactiveFormsModule,
    MaterialModule,
    FileUploadModule,
    NgxDatatableModule,
    DataTableModule,
    Tree2Module,
    TreeModule,
    TreeviewModule.forRoot(),
    TabsModule.forRoot(),
    DirectivesModule,
       // NgxPaginationModule,
    RouterModule.forChild(OrderManagementRoutes)

  ],
  declarations: [
  AddOrderItemSentComponent,
  AddOrderComponent,
  OrderSentMainComponent,
  UpdateOrderComponent,
  OrderReceivedMainComponent,
  AddOrderItemReceivedComponent,
  UpdateOrderReceivedComponent

  
  ]
})
export class OrderManagementModule { }

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

import {InvoiceManagementRoutes } from './invoice-management.routing';

import { SharedModule } from '../SharedModule';

import { InvoiceSentComponent } from './invoice-sent/invoice/search/invoiceSent.component';
import { AddInvoiceComponent } from './invoice-sent/invoice/add/add-invoice.component';
import { UpdateInvoiceComponent } from './invoice-sent/invoice/update/update-invoice.component';
import {DataTableModule} from "angular2-datatable";
import { AddInvoiceItemComponent } from './invoice-sent/invoice-item/add/add-invoice-item.component';
import { InvoiceReceivedComponent } from './invoice-received/search/invoiceReceived.component';
import { ViewInvoiceComponent } from './invoice-received/veiw/view-invoice.component';
import { InvoiceDetailsComponent } from './invoice-details/search/invoiceDetails.component';
import { TableModule } from 'ngx-easy-table';
import { UpdateOrderItemComponent } from './invoice-sent/invoice-item/update/update-order-item.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    AccordionModule.forRoot(),
    ReactiveFormsModule,
    MaterialModule,
    FileUploadModule,
    NgxDatatableModule,
    DataTableModule,
    Tree2Module,
    TreeModule,
	TableModule,
    TreeviewModule.forRoot(),
    TabsModule.forRoot(),
    DirectivesModule,
    RouterModule.forChild(InvoiceManagementRoutes)

  ],
  declarations: [
  InvoiceSentComponent,
  AddInvoiceComponent,
  UpdateInvoiceComponent,
  AddInvoiceItemComponent,
  InvoiceReceivedComponent,
  ViewInvoiceComponent,
  InvoiceDetailsComponent,
  UpdateOrderItemComponent
  ]
})
export class InvoiceManagementModule { }

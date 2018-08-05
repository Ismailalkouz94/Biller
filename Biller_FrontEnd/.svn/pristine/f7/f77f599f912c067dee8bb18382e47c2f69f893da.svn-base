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

import {TransactionsRoutes } from './transactions.routing';
import { BillsComponent } from './bills/bills.component';
import {DataTableModule} from "angular2-datatable";
@NgModule({
  imports: [
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
    RouterModule.forChild(TransactionsRoutes)

  ],
  declarations: [

  BillsComponent,

 
  
  ]
})
export class TransactionsModule { }

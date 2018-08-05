import { NgModule, ModuleWithProviders } from '@angular/core';
import { TreeviewModule } from './lib';
import { DropdownTreeviewSelectComponent } from './dropdown-treeview-select/dropdown-treeview-select.component';
import { DropdownTreeviewSelectDemoComponent } from './dropdown-treeview-select/dropdown-treeview-select-demo.component';
import {CommonModule} from '@angular/common';
import { FormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {CalendarModule} from './shared/service/calendar';
import {TabViewModule} from './shared/service/tabview';
import {CodeHighlighterModule} from './shared/service/codehighlighter';
import { TableModule } from 'ngx-easy-table';
import { TabsModule  } from 'ngx-bootstrap';

import { AngularDraggableModule } from 'angular2-draggable';

import { DialogComponent } from './dialog/dialog.component';
import { AboutComponent } from './Users/about/about.component';
import { NavbarComponent } from './Users/core/navbar/navbar.component';
// import { CustomerComponent } from './Users/customer/customer.component';
// import { CustomersComponent } from './Users/customers/customers.component';
import { OrdersComponent } from './Users/orders/orders.component';
import { MapComponent } from './Users/shared/map/map.component';
import { MapPointComponent } from './Users/shared/map/mapPoint.component';
import { DisabledOnSelectorDirective } from './disabled-on-selector.directive';


@NgModule({
imports: [CommonModule,FormsModule,CalendarModule,TabViewModule,TableModule,TabsModule.forRoot(),AngularDraggableModule,
  CodeHighlighterModule,TreeviewModule.forRoot()],
  declarations: [ DropdownTreeviewSelectComponent ,DropdownTreeviewSelectDemoComponent,DialogComponent,NavbarComponent,OrdersComponent

    // CustomersComponent,CustomerComponent,
    ,MapComponent,MapPointComponent,
    DialogComponent, DisabledOnSelectorDirective],

  exports: [ 

    DropdownTreeviewSelectComponent,DropdownTreeviewSelectDemoComponent,DialogComponent,
    CommonModule, FormsModule ,CalendarModule,TabViewModule,TabsModule,AngularDraggableModule,
    CodeHighlighterModule,TableModule,TreeviewModule,DialogComponent,NavbarComponent,OrdersComponent,MapComponent,MapPointComponent
  ]


})
export class SharedModule {}
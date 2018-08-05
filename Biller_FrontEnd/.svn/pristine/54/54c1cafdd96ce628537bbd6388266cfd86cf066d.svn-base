import { NgModule, ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { TranslateModule, TranslateLoader, TranslateStaticLoader } from 'ng2-translate/ng2-translate';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';
import {DataTableModule} from "angular2-datatable";
import { BankComponent } from './bank.component';
import { BankRoutes } from './bank.routing';
import { SharedModule } from '../SharedModule';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


// import { DxDataGridComponent,
//          DxDataGridModule,
//          DxSelectBoxModule,
//          DxCheckBoxModule } from 'devextreme-angular';
         import { HttpModule, Http } from '@angular/http';
import { Order, Service } from '../shared/service/grid';
export function createTranslateLoader(http: Http) {
  return new TranslateStaticLoader(http, 'assets/i18n', '.json');
}
@NgModule({
  imports: [
    // DxDataGridModule,
    // DxSelectBoxModule,
    // DxCheckBoxModule,
    CommonModule,
    FormsModule,
    SharedModule,
    MaterialModule,
    DataTableModule,
    ReactiveFormsModule,
    FileUploadModule,
    NgxDatatableModule,
    TreeModule,
    DirectivesModule,
    TranslateModule.forRoot({
      provide: TranslateLoader,
      useFactory: (createTranslateLoader),
      deps: [Http]
   }),
    RouterModule.forChild(BankRoutes)

  ],
  declarations: [
    BankComponent
  ]
})
export class BankModule { }

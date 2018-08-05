import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';
import { TabsModule  } from 'ngx-bootstrap';

import { UploadInvoicesViewComponent } from './uploadinvoices-view.component';
import { UploadInvoicesViewRoutes } from './uploadinvoices-view.routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    FileUploadModule,
    TabsModule.forRoot(),
    NgxDatatableModule,
    TreeModule,
    DirectivesModule,
    RouterModule.forChild(UploadInvoicesViewRoutes)

  ],
  declarations: [
  UploadInvoicesViewComponent
  ]
})
export class UploadInvoicesViewModule { }

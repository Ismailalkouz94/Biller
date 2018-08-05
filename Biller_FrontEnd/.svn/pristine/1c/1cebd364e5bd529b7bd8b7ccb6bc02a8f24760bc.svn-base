import { NgModule, ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';

import { PushNotificationsComponent } from './push-notifications.component';
import { PushNotificationsRoutes } from './push-notifications.routing';
import { TableModule } from 'ngx-easy-table';
import { CustomersComponent } from './customers/customers.component';
import { SharedModule } from '../SharedModule';
import { I18n } from '../lib/i18n';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    MaterialModule,
    ReactiveFormsModule,
    FileUploadModule,
    NgxDatatableModule,
    TreeModule,
    TableModule,
    DirectivesModule,
    RouterModule.forChild(PushNotificationsRoutes)

  ],
  declarations: [
    PushNotificationsComponent,
    CustomersComponent
  ],
  providers: [
    I18n
],
})
export class PushNotificationsModule { }

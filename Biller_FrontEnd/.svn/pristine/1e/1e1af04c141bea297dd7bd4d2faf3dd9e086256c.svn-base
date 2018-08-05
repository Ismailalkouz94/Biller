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

import { BillersComponent } from './billers/billers.component';
import { AdminstrationRoutes } from './adminstration.routing';
import {ConfirmDialogModule} from '../shared/confirmdialog/confirmdialog';
import { PartyComponent } from './party/party.component';
import { SharedModule } from '../SharedModule';
import { LoadingModule, ANIMATION_TYPES } from 'ngx-loading';

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    FileUploadModule,
    NgxDatatableModule,
    TreeModule,
    ConfirmDialogModule,
     TabsModule.forRoot(),
    DirectivesModule,
    RouterModule.forChild(AdminstrationRoutes),
    LoadingModule,
    
  ],
  declarations: [ 
      BillersComponent,
      PartyComponent
  ]
})

export class AdminstrationModule {}

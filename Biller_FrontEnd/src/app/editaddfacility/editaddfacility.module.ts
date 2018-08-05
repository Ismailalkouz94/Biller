import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';


import { EditAddFacilityComponent } from './editaddfacility.component';
import { EditAddFacilityRoutes } from './editaddfacility.routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    FileUploadModule,
    NgxDatatableModule,
    TreeModule,
    DirectivesModule,
    RouterModule.forChild(EditAddFacilityRoutes)

  ],
  declarations: [
  EditAddFacilityComponent
  ]
})
export class EditAddFacilityModule { }

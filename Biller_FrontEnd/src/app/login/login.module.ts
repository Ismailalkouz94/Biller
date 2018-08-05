import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';


import { LoginComponent } from './login.component';
import { LoginRoutes } from './login.routing';
import { LoadingModule, ANIMATION_TYPES } from 'ngx-loading';

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
    LoadingModule.forRoot({
			animationType: ANIMATION_TYPES.wanderingCubes,
			backdropBackgroundColour: 'rgba(0,0,0,0.1)', 
			backdropBorderRadius: '4px',
			primaryColour: '#ffffff', 
			secondaryColour: '#ffffff', 
			tertiaryColour: '#ffffff'
		}),
    RouterModule.forChild(LoginRoutes)

  ],
  exports: [
    LoginComponent
  ],
  declarations: [
  LoginComponent
  ],

})
export class LoginModule { }

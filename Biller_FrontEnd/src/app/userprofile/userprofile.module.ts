import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { UserprofileComponent } from './userprofile.component';
import { UserprofileRoutes } from './userprofile.routing';

import { TabsModule  } from 'ngx-bootstrap';
import { SharedModule } from '../SharedModule';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    TabsModule.forRoot(),
    MaterialModule,
    SharedModule,
    RouterModule.forChild(UserprofileRoutes)
  ],
  declarations: [
    UserprofileComponent
  ]
})
export class UserprofileModule { }

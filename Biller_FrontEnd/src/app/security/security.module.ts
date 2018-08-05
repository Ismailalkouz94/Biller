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
import { MenusComponent } from './menus/menus.component';

import { MenuRoleComponent } from './menuRole/search/menuRole.component';
import { AddMenuRoleComponent } from './menuRole/add/add-menuRole.component';
import { RoleGroupComponent } from './roleGroup/search/roleGroup.component';
import { AddRoleGroupComponent } from './roleGroup/add/add-roleGroup.component';

import { UsersComponent } from './users/users.component';
import { UserRoleComponent } from './userRole/userRole.component';

import { SecurityRoutes } from './security.routing';
import {Tree2Module} from '../shared/tree/tree';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AccordionModule.forRoot(),
    ReactiveFormsModule,
    MaterialModule,
    FileUploadModule,
    NgxDatatableModule,
    Tree2Module,
    TreeModule,
    TreeviewModule.forRoot(),
    TabsModule.forRoot(),
    DirectivesModule,
    RouterModule.forChild(SecurityRoutes)

  ],
  declarations: [
  MenusComponent,
  MenuRoleComponent,
  RoleGroupComponent,
  AddRoleGroupComponent,
  UsersComponent,
  UserRoleComponent,
  AddMenuRoleComponent
  ]
})
export class SecurityModule { }

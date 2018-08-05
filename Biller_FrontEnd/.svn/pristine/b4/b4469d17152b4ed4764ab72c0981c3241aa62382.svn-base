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
import { ComponentComponent } from './component/component.component';
import { GlobalItemComponent } from './globalItem/globalItem.component';
import { GlobalTypeComponent } from './globalType/globalType.component';
import { MenuRoleComponent } from './menuRole/menuRole.component';
import { PartyGroupComponent } from './partyGroup/partyGroup.component';
import { PartyRoleComponent } from './partyRole/partyRole.component';
import { PersonComponent } from './person/person.component';
import { RoleGroupComponent } from './roleGroup/roleGroup.component';
import { RoleTypeComponent } from './roleType/roleType.component';
import { UserLoginComponent } from './userLogin/userLogin.component';
import { UserRoleComponent } from './userRole/userRole.component';
import { ComponentSubComponent } from './component_sub/component_sub.component';
import { AdminSettingsRoutes } from './admin-settings.routing';
import {Tree2Module} from '../shared/tree/tree';
import { NoConflictStyleCompatibilityMode } from '@angular/material'

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AccordionModule.forRoot(),
    ReactiveFormsModule,
    MaterialModule,
    FileUploadModule,
    NoConflictStyleCompatibilityMode,
    NgxDatatableModule,
    Tree2Module,
    TreeModule,
    TreeviewModule.forRoot(),
    TabsModule.forRoot(),
    DirectivesModule,
    RouterModule.forChild(AdminSettingsRoutes)

  ],
  declarations: [
  MenusComponent,
  ComponentComponent,
  ComponentSubComponent,
  GlobalItemComponent,
  GlobalTypeComponent,
  MenuRoleComponent,
  PartyGroupComponent,
  PartyRoleComponent,
  PersonComponent,
  RoleGroupComponent,
  RoleTypeComponent,
  UserLoginComponent,
  UserRoleComponent,
  ]
})
export class AdminSettingsModule { }

import { Routes } from '@angular/router';

import { MenusComponent } from './menus/menus.component';
import { MenuRoleComponent } from './menuRole/search/menuRole.component';

import { RoleGroupComponent } from './roleGroup/search/roleGroup.component';
import { AddRoleGroupComponent } from './roleGroup/add/add-roleGroup.component';

import { UsersComponent } from './users/users.component';
import { UserRoleComponent } from './userRole/userRole.component';
import { AddMenuRoleComponent } from './menuRole/add/add-menuRole.component';


export const SecurityRoutes: Routes = [{
  path: '',
  redirectTo: 'menus',
  pathMatch: 'full',
},{
  path: '',
  children: [{
    path: 'menus',
    component: MenusComponent
  },{
    path: 'roleGroup',
    component: RoleGroupComponent
  },{
    path: 'add-roleGroup',
    component: AddRoleGroupComponent
  },{
    path: 'menuRole',
    component: MenuRoleComponent
  },{
    path: 'add-menuRole',
    component: AddMenuRoleComponent
  },
  
  {
    path: 'users',
    component: UsersComponent
  },{
    path: 'userRole',
    component: UserRoleComponent
  }]
}];

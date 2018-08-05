import { Routes } from '@angular/router';

import { PreloadModulesStrategy } from './core/strategies/preload-modules.strategy';

// import { CustomerComponent } from './customer/customer.component';
// import { CustomersComponent } from './customers/customers.component';
// import { CustomerEditComponent } from './customer/customer-edit.component';
import { AboutComponent } from './about/about.component';

export const UsersRoutes: Routes = [{
 path: '',
  redirectTo: 'customers',
  pathMatch: 'full',
},{
  path: '',
  children: [
    // {
  //   path: 'customers',
  //   component: CustomersComponent
  // },{
  //   path: 'orders',
  //   component: CustomerEditComponent
  // },
  {
    path: 'about',
    component: AboutComponent
  }]
}];


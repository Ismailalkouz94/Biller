import { Routes } from '@angular/router';

import { PushNotificationsComponent } from './push-notifications.component';
import { CustomersComponent } from './customers/customers.component';

export const PushNotificationsRoutes: Routes = [{
  path: '',
  children: [{
    path: '',
    component: PushNotificationsComponent
  },
{
  path:'toCustomres',
  component:CustomersComponent
}]
}];

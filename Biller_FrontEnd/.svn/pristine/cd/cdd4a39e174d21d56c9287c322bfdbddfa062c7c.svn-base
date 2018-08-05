import { Routes } from '@angular/router';

import { AddOrderComponent } from './order-sent/order/add/add-order.component';
import { UpdateOrderComponent } from './order-sent/order/update/update-order.component';
import { OrderSentMainComponent } from './order-sent/order/search/orderSent.component';

import { AddOrderItemSentComponent } from './order-sent/order-item/add/add-order-item.component';
import { OrderReceivedMainComponent } from './order-received/order/search/orderReceived.component'
import { AddOrderItemReceivedComponent } from './order-received/order-item/add/add-order-item.component';
import { UpdateOrderReceivedComponent } from './order-received/order/update/update-orderReceived.component';

export const OrderManagementRoutes: Routes = [{
  path: '',
  redirectTo: 'order-sent',
  pathMatch: 'full',
},


{
  path: 'order-sent',
  component: OrderSentMainComponent
},
{
  path: 'add-order',
  component: AddOrderComponent
},
{
  path: 'update-order',
  component: UpdateOrderComponent
}, {
  path: 'add-orderItemSent',
  component: AddOrderItemSentComponent
},
{
  path: 'order-received',
  component: OrderReceivedMainComponent
},{
  path: 'order-orderReceived',
  component: UpdateOrderReceivedComponent
},
{
  path: 'add-orderItemReceived',
  component: AddOrderItemReceivedComponent
}

];

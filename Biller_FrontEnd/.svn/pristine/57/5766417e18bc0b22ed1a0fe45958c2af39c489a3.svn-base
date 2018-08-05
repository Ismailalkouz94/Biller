import { Routes } from '@angular/router';


import { InvoiceSentComponent } from './invoice-sent/invoice/search/invoiceSent.component';
import { AddInvoiceComponent } from './invoice-sent/invoice/add/add-invoice.component';
import { UpdateInvoiceComponent } from './invoice-sent/invoice/update/update-invoice.component';
import { AddInvoiceItemComponent } from './invoice-sent/invoice-item/add/add-invoice-item.component';
import { InvoiceReceivedComponent } from './invoice-received/search/invoiceReceived.component';
import { ViewInvoiceComponent } from './invoice-received/veiw/view-invoice.component';
import { InvoiceDetailsComponent } from './invoice-details/search/invoiceDetails.component';


export const InvoiceManagementRoutes: Routes = [ {
  path: '',
  redirectTo: 'invoice-sent',
  pathMatch: 'full',
},{
  path: 'invoice-sent',
  component: InvoiceSentComponent
},{
  path: 'add-invoice',
  component: AddInvoiceComponent
},{
  path: 'update-invoice',
  component: UpdateInvoiceComponent
},{
  path: 'add-invoiceItem',
  component: AddInvoiceItemComponent
},
{
  path: 'invoice-received',
  component: InvoiceReceivedComponent
}
,{
  path:'view-invoice',
  component:ViewInvoiceComponent
},
{
  path:'invoice-details',
  component:InvoiceDetailsComponent
}

];

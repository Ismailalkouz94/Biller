import { Routes } from '@angular/router';

import { UploadInvoicesViewComponent } from './uploadinvoices-view.component';

export const UploadInvoicesViewRoutes: Routes = [{
  path: '',
  children: [{
    path: '',
    component: UploadInvoicesViewComponent
  }]
}];

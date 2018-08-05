import { NgModule, ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { UsersRoutes } from './Users.routing';

import { LoginModule } from './login/login.module';
import { SharedModuleSub } from './shared/shared.module';
// import { CustomersCardComponent } from './customers/customers-card.component';
// import { CustomersGridComponent } from './customers/customers-grid.component';


// import { CustomerEditComponent } from './customer/customer-edit.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { GrowlerModule } from './core/growler/growler.module';
import { ModalModule } from './core/modal/modal.module';
import { OverlayModule } from './core/overlay/overlay.module';
import { NavbarComponent } from './core/navbar/navbar.component';

import { DataService } from './core/services/data.service';
import { FilterService } from './core/services/filter.service';
import { SorterService } from './core/services/sorter.service';
import { TrackByService } from './core/services/trackby.service';
import { DialogService } from './core/services/dialog.service';
import { EnsureModuleLoadedOnceGuard } from './core/ensureModuleLoadedOnceGuard';
import { AuthService } from './core/services/auth.service';
import { EventBusService } from './core/services/event-bus.service';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';
import { SharedModule } from '../SharedModule';

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    FileUploadModule,
    NgxDatatableModule,
    TreeModule,
    DirectivesModule,
    SharedModuleSub,
 RouterModule, HttpClientModule, GrowlerModule, ModalModule, OverlayModule,
    CommonModule,   
    LoginModule,         
    SharedModule ,
RouterModule.forChild(UsersRoutes)    

  ],
  declarations: [
// CustomerEditComponent,
// CustomersCardComponent, CustomersGridComponent,


  ],
  exports: [GrowlerModule, RouterModule, HttpClientModule, ModalModule, OverlayModule],

  providers: [SorterService, FilterService, DataService, TrackByService,
    DialogService, AuthService, EventBusService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    }
  ]

})
export class UsersModule { }

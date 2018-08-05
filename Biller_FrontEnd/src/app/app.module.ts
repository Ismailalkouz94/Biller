import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { CommonModule } from '@angular/common';
import { HttpModule, Http } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@angular/material';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { TourNgBootstrapModule } from 'ngx-tour-ng-bootstrap';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { TranslateModule, TranslateLoader, TranslateStaticLoader } from 'ng2-translate/ng2-translate';
import 'hammerjs';
import { LoginComponent } from './login/login.component';
import { SilkAppComponent } from './app.component';
import { AppRoutes } from "./app-routing.module";
import { MainComponent } from './main/main.component';
import { AuthLayoutComponent } from './auth/auth-layout.component';
import { MenuToggleModule } from './core/menu/menu-toggle.module';
import { MenuItems } from './core/menu/menu-items/menu-items';
import { HorizontalMenuItems } from './core/menu/menu-items/horizontal-menu-items';
import { PageTitleService } from './core/page-title/page-title.service';
import { AuthGuard } from './shared/guards/auth-guard.service';
import { CoreModule } from './core/core.module';
import { DropdownTreeviewSelectModule } from './dropdown-treeview-select';
import { NodeService } from './shared/guards/tree/nodeservice';

import { ConfirmDialogModule } from './shared/service/confirmdialog';
import { ToastModule } from 'ng2-toastr/ng2-toastr';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { GrowlerModule } from './Users/core/growler/growler.module';
import { ModalModule } from './Users/core/modal/modal.module';
import { OverlayModule } from './Users/core/overlay/overlay.module';

import { LoadingModule, ANIMATION_TYPES } from 'ngx-loading';

import { DataService } from './Users/core/services/data.service';

import { FilterService } from './Users/core/services/filter.service';
import { SorterService } from './Users/core/services/sorter.service';
import { TrackByService } from './Users/core/services/trackby.service';
import { DialogService } from './Users/core/services/dialog.service';
import { EnsureModuleLoadedOnceGuard } from './Users/core/ensureModuleLoadedOnceGuard';
import { AuthService } from './Users/core/services/auth.service';
import { EventBusService } from './Users/core/services/event-bus.service';
import { AuthInterceptor } from './Users/core/interceptors/auth.interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UsersService } from './shared/service/login.service';
import { NotificationsService } from './notifications/notifications.service'
import { UtilService } from './shared/service/util.service';
import { ConfirmationDialog } from './confirm-dialog/confirmation-dialog';
import { SharedModule } from './SharedModule';

export function createTranslateLoader(http: Http) {
	return new TranslateStaticLoader(http, 'assets/i18n', '.json');
}

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
	suppressScrollX: true
};

@NgModule({
	imports: [
		NgbModule.forRoot(),
		CommonModule,
		BrowserModule,
		SharedModule,
		ConfirmDialogModule,
		BrowserAnimationsModule,
		ToastModule.forRoot(),
		FormsModule,
		ReactiveFormsModule,
		FlexLayoutModule,
		MaterialModule,
		RouterModule.forRoot(AppRoutes),
		CoreModule,
		PerfectScrollbarModule,
		MenuToggleModule,
		HttpModule,
		LoadingModule.forRoot({
			animationType: ANIMATION_TYPES.wanderingCubes,
			backdropBackgroundColour: 'rgba(0,0,0,0.1)', 
			backdropBorderRadius: '4px',
			primaryColour: '#ffffff', 
			secondaryColour: '#ffffff', 
			tertiaryColour: '#ffffff'
		}),
		TourNgBootstrapModule.forRoot(),
		TranslateModule.forRoot({
			provide: TranslateLoader,
			useFactory: (createTranslateLoader),
			deps: [Http]
		}),
	],
	declarations: [
  

		
		SilkAppComponent,
		MainComponent,
		AuthLayoutComponent,
		ConfirmationDialog
	],
	bootstrap: [SilkAppComponent],
	entryComponents: [ConfirmationDialog],
	providers: [
		SorterService, FilterService, DataService, TrackByService, LoginComponent,
		DialogService, AuthService, EventBusService,
		NodeService,
		MenuItems,
		ConfirmationDialog,
		HorizontalMenuItems,
		PageTitleService,
		UsersService,
		AuthGuard,
		NotificationsService,
		UtilService,

		{
			provide: PERFECT_SCROLLBAR_CONFIG,
			useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG,
		}
	],



	exports: [


	]
})
export class SilkAppModule { }



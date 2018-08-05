import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';

import { MainComponent }   from './main/main.component';
import { AuthLayoutComponent } from './auth/auth-layout.component';
import { AuthGuard } from './shared/guards/auth-guard.service';

import { RouterModule, PreloadAllModules, NoPreloading } from '@angular/router';

import { PreloadModulesStrategy } from './Users/core/strategies/preload-modules.strategy';

      
export const AppRoutes: Routes = [{
  
  path: '',
  redirectTo: 'login',
  pathMatch: 'full',
},
// },{
  
//   path: '**',
//   redirectTo: 'login',
//   pathMatch: 'full',
// },
{
  path: '',
  component: AuthLayoutComponent,
  children: [{
    path: 'login',
      loadChildren: './login/login.module#LoginModule'
  },{
    path: 'signup',
      loadChildren: './signup/signup.module#SignupModule'
  }]
},{
  path: '',
  component: MainComponent,
  canActivateChild: [AuthGuard],
  children: [{
    path: 'dashboard',
        loadChildren: './dashboard/dashboard.module#DashboardModule',
  },
  {
path:'notifications',
loadChildren:'./pushnotificationcenter/push-notifications.module#PushNotificationsModule'
  },
  {
    path: 'emails',
    loadChildren: './emails/emails.module#EmailsModule'
  },{
      path: 'chat',
    loadChildren: './chat/chat.module#ChatModule'
  },{
      path: 'features',
    loadChildren: './features/features.module#FeaturesModule'
  },{
    path: 'components',
    loadChildren: './components/components.module#ComponentsModule'
  },{
    path: 'icons',
    loadChildren: './icons/icons.module#IconsModule'
  },{
    path: 'cards',
    loadChildren: './cards/cards.module#CardsModule'
  },{
    path: 'forms',
    loadChildren: './forms/forms.module#FormsDemoModule'
  },{
    path: 'tables',
    loadChildren: './tables/tables.module#TablesDemoModule'
  },{
    path: 'data-tables',
    loadChildren: './data-tables/datatables.module#DataTablesDemoModule'
  },{
    path: 'chart',
    loadChildren: './chart/charts.module#ChartsDemoModule'
  },{
    path: 'maps',
    loadChildren: './maps/maps.module#MapsDemoModule'
  },{
    path: 'pages',
    loadChildren: './custom-pages/pages.module#PagesDemoModule'
  },{
    path: 'user-pages',
    loadChildren: './user-pages/users.module#UsersModule'
  },{
    path: 'calendar',
    loadChildren: './calendar/calendar.module#CalendarDemoModule'
  },{
    path: 'media',
    loadChildren: './media/media.module#MediaModule'
  },{
    path: 'editor',
    loadChildren: './editor/editor.module#EditorDemoModule'
  },{
    path: 'billerprofile',
    loadChildren: './userprofile/userprofile.module#UserprofileModule'
  },{
    path: 'bank',
    loadChildren: './bank/bank.module#BankModule'
  },{
    path: 'enquiry',
    loadChildren: './enquiry/enquiry.module#EnquiryModule'
  },{
    path: 'adminsettings',
    loadChildren: './admin-settings/admin-settings.module#AdminSettingsModule'
  },{
    path: 'transactions',
    loadChildren: './transactions/transactions.module#TransactionsModule'

  },{
    path: 'order',
    loadChildren: './order-management/order-management.module#OrderManagementModule'

  },{
    path: 'invoice',
    loadChildren: './invoice-management/invoice-management.module#InvoiceManagementModule'

  },{
    path: 'config',
    loadChildren: './config/config.module#ConfigModule'
  },{
      path: 'security',
    loadChildren: './security/security.module#SecurityModule'

  },{
    path: 'adminstration',
    loadChildren: './adminstration/adminstration.module#AdminstrationModule'

  },{
  path: 'editaddfacility',
    loadChildren: './editaddfacility/editaddfacility.module#EditAddFacilityModule'
  },{
    path: 'party',
    loadChildren: './party/party.module#PartyModule'
  },{
    path: 'person',
    loadChildren: './party/person/person.module#PersonModule'
  },{
    path: 'invoice',
    loadChildren: './invoice/invoice.module#InvoiceModule'
  },{
    path: 'users',
    loadChildren: './Users/Users.module#UsersModule'
  }]
}]; 


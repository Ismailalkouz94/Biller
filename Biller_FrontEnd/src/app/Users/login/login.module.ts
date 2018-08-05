import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModuleSub } from '../shared/shared.module';
import { LoginRoutingModule } from './login-routing.module';

@NgModule({
  imports: [ ReactiveFormsModule, SharedModuleSub, LoginRoutingModule ],
  declarations: [ LoginRoutingModule.components ]
})
export class LoginModule { }

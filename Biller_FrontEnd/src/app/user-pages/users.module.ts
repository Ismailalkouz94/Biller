import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { DirectivesModule } from '../core/widgster/directives.module';

import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserListComponent } from './user-list/userlist.component';

import { UsersRoutes } from './users.routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    DirectivesModule,
    RouterModule.forChild(UsersRoutes)
  ],
  declarations: [ 
    UserProfileComponent,
    UserListComponent
  ]
})

export class UsersModule {}

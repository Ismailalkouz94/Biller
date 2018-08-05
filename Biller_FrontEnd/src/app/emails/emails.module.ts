import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { QuillModule } from 'ngx-quill';

import { InboxComponent } from './inbox.component';
import { MailService } from "./mail.service";
import { InboxComposeComponent } from './inbox-compose/inbox-compose.component';

import { EmailsRoutes } from './emails.routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    QuillModule,
    RouterModule.forChild(EmailsRoutes)
  ],
  declarations: [ 
    InboxComponent,
    InboxComposeComponent,
  ],
  entryComponents: [
    InboxComposeComponent,
  ],
  providers:[
    MailService
  ]
})

export class EmailsModule {}

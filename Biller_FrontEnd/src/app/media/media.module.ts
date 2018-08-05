import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { DirectivesModule } from '../core/widgster/directives.module';

import { MediaGridComponent}  from './grid/media-grid.component';
import { MediaListComponent}  from './list/media-list.component';

import { MediaRoutes } from './media.routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    DirectivesModule,
    RouterModule.forChild(MediaRoutes)
  ],
  declarations: [ 
    MediaGridComponent,
    MediaListComponent,
  ]
})

export class MediaModule {}

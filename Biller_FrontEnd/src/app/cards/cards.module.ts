import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MdIconModule} from '@angular/material';
import { DirectivesModule } from '../core/widgster/directives.module';

import { BasicCardsComponent}  from './basic/basic-cards.component';
import { AdvanceCardsComponent}  from './advance/advance-cards.component';

import { CardsRoutes } from './cards.routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MdIconModule,
    DirectivesModule,
    RouterModule.forChild(CardsRoutes)
  ],
  declarations: [ 
    BasicCardsComponent,
    AdvanceCardsComponent
  ]
})

export class CardsModule {}

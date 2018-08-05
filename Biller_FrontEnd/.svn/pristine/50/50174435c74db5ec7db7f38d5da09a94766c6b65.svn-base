import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { TreeModule } from 'angular-tree-component';
import { DirectivesModule } from '../core/widgster/directives.module';
import { TreeviewItem, TreeviewConfig } from '../lib';


import { FavoriteComponent } from './favorite-party/favorite.component';

import { AddCategoryComponent } from './category/add/add-category.component';
import { MainCategoryComponent } from './category/search/main-category.component';
import { UpdateCategoryComponent } from './category/update/update-category.component';

import {ConfigRoutes } from './config.routing';
import {DataTableModule} from "angular2-datatable";
import { MainItemComponent } from './item/search/main-item.component';
import { AddItemComponent } from './item/add/add-item.component';
import { UpdateItemComponent } from './item/update/update-item.component';


import { MainUnitComponent } from './unit/search/main-unit.component';
import { AddUnitComponent } from './unit/add/add-unit.component';
import { UpdateUnitComponent } from './unit/update/update-unit.component';

import { TreeviewModule } from '../lib';
import { SharedModule } from '../SharedModule';
import {Tree2Module} from '../shared/tree/tree';

@NgModule({
  imports: [
    SharedModule ,
    CommonModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    FileUploadModule,
    NgxDatatableModule,
    
    TreeModule,
    Tree2Module,
    DataTableModule,
    NgxDatatableModule,
    DirectivesModule,
    TreeviewModule.forRoot(),
    RouterModule.forChild(ConfigRoutes)

  ],
  declarations: [
    MainItemComponent,
      AddCategoryComponent,
       MainCategoryComponent,
    AddItemComponent,
    UpdateCategoryComponent,

    UpdateItemComponent,
      AddUnitComponent,
    UpdateUnitComponent,
    MainUnitComponent,
    FavoriteComponent
    
  ]
})
export class ConfigModule {}

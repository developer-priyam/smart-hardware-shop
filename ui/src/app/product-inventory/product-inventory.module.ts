import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductInventoryRoutingModule } from './product-inventory-routing.module';
import { FocusBannerComponent } from './focus-banner/focus-banner.component';
import { ProdductInventoryComponent } from './prodduct-inventory.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    FocusBannerComponent,
    ProdductInventoryComponent
  ],
  imports: [
    CommonModule,
    ProductInventoryRoutingModule,
    SharedModule
  ],
  exports: [
    ProdductInventoryComponent
  ]
})
export class ProductInventoryModule { }

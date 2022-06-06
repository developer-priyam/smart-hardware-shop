import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { ProductInventoryModule } from '../product-inventory/product-inventory.module';
import { ShoppingCartModule } from '../shopping-cart/shopping-cart.module';


@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    ProductInventoryModule,
    ShoppingCartModule
  ]
})
export class DashboardModule { }

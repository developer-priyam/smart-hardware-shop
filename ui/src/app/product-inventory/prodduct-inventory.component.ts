import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { LoggerService } from '../shared/service/logger.service';
import { SharedService } from '../shared/service/shared.service';
import { Product } from './model/product.model';
import { ProductInventoryService } from './service/product-inventory.service';

@Component({
  selector: 'app-prodduct-inventory',
  templateUrl: './prodduct-inventory.component.html',
  styleUrls: ['./prodduct-inventory.component.css'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class ProdductInventoryComponent implements OnInit {
  productList: Product[] = [];
  highlightedProducts: Product[] = [];
  constructor(private inventory: ProductInventoryService, 
              private sharedService: SharedService,
              private logger: LoggerService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.inventory.getProducts()
    .subscribe(
      (response: Product[]) => {
        this.productList = response;
        this.getHighlightedProducts();
      },
      ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
    );
  }

  getProductStatus(product: Product): string {
    return (product.description.availableCount > 0) ? "In Stock" : "Sold Out";
  }

  getProductNames(): string[] {
    return this.productList.map(product => product.description.name);
  }

  addItemToCart(productId: string): void {
    this.sharedService.addToCart.emit(this.productList.find(product => product.productId.id === productId));
  }

  getHighlightedProducts(): void {
    this.inventory.getBanner()
    .subscribe(
      (products: Product[]) => {
        this.highlightedProducts = products;
    },
    ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
    );
  }

  getFilteredProducts(event: any): void {
    console.log(event);
    const searchText = {text: event.toLowerCase()};
    this.inventory.searchProducts(searchText)
    .subscribe(
      (response => this.productList = response),
      ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
    );
  }

  loadProductDetails(product: Product): void {
    this.logger.openSnackBar('We are adding Data for Product Details. Be Patient and Comeback Soon!', 'close')
  }
}

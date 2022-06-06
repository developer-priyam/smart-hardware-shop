import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ChangeDetectionStrategy, Input, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ProductId } from 'src/app/product-inventory/model/product-id.model';
import { Product } from 'src/app/product-inventory/model/product.model';
import { LoggerService } from 'src/app/shared/service/logger.service';
import { SharedService } from 'src/app/shared/service/shared.service';
import { CartItem } from '../model/cart-item.model';
import { ShoppingCartService } from '../service/shopping-cart.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class ProductListComponent implements OnInit, OnDestroy {
  cartItems: CartItem[] = [];
  productList: Product[] = [];
  userId: string = '';
  addToCartSubscription: Subscription;
  userFetchedSubscription: Subscription;

  constructor(private cartService: ShoppingCartService, 
              private sharedService: SharedService,
              private logger: LoggerService) { 
    this.addToCartSubscription = this.sharedService.addToCart.subscribe(product => {
      this.addItemToCart(product);
    });
    this.userFetchedSubscription = this.sharedService.userFetched.subscribe(user => {
      this.userId = user.userId;
      this.getCartItems(this.userId);
    });
  }

  ngOnInit(): void {}

  getCartItems(userId: string): void {
    this.cartService.getProductsInCart(userId)
    .subscribe(
      (response => {
        if (response != null) {
          this.cartItems = response;
          const productIds: string[] = this.cartItems.map(item => item.productId);
          this.getProductList(productIds);
        }
       }),
       ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
    )
  }

  getProductStatus(product: Product): string {
    return (product.description.availableCount > 0) ? "In Stock" : "Sold Out";
  }

  getProductList(productIds: string[]): void {
    const ids : ProductId[] = [];
    productIds.forEach(productId => ids.push({id: productId}));
    this.cartService.getProductsForCartItems(ids)
    .subscribe(
      (response => this.productList = response),
      ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
    );
  }

  addItemToCart(product: Product): void {
    let quantity = 1;
    let cartItem: CartItem;
    if (this.isProductAlreadyInCart(product)) {
      this.logger.openSnackBar('Product Already in the cart. Check Cart', 'close');
    } else {
      cartItem = {productId: product.productId.id, quantity};
      this.cartItems.push(cartItem);
      this.productList.push(product);
      this.cartService.addProductInCart(this.userId, cartItem).subscribe(response => {
        this.logger.openSnackBar('Product Added.', 'close')}); 
    }
  }

  isProductAlreadyInCart(product: Product): boolean {
    return this.productList.find(pd => pd.productId.id === product.productId.id) !== undefined;
  }

  getCartTotal(): number {
    let total = 0;
    this.productList.forEach(product => {
      const item = this.cartItems.find(cartItem => cartItem.productId === product.productId.id);
      if (item !== undefined) {
        total += (product.description.price * item?.quantity);
      } else {
        total += (product.description.price);
      }
      
    });
    return total;
  }

  getQuantity(productId: ProductId): number {
    const found: CartItem | undefined = this.cartItems.find(item => item.productId === productId.id);
    return (found !== undefined) ? found.quantity : 0;
  }

  cleanCart(): void {
    this.cartItems = [];
    this.productList = [];
    // Add popup Dialog
    this.cartService.clearUserCart(this.userId).subscribe(
      response => this.logger.openSnackBar('cart cleared', 'close'),
      ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close')));
  }

  buyNow(): void {
    this.logger.openSnackBar("This feature will be available in next release. ", "close");
  }

  updateQuantity(product: Product, value: number): void {
    if (value > 0) {
      const cartItem: CartItem = {productId: product.productId.id, quantity: value};
      this.cartItems = this.cartItems.filter(item => item.productId !== product.productId.id);
      this.cartItems.push(cartItem);
      this.cartService.updateProductInCart(this.userId, cartItem).subscribe(
        (response => this.logger.openSnackBar('Product QUantity Updated', 'close')),
        ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
      );
    } else {
      this.cartItems = this.cartItems.filter(item => item.productId !== product.productId.id);
      this.productList = this.productList.filter(item => item.productId.id !== product.productId.id);
      this.cartService.deleteProductFromCart(this.userId, product.productId.id).subscribe(
        (response => this.logger.openSnackBar('Product Removed Ffrom Cart', 'close')),
        ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
      );
    }
    
  }

  ngOnDestroy(): void {
    this.addToCartSubscription.unsubscribe();
    this.userFetchedSubscription.unsubscribe();
  }

}

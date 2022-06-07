import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductId } from 'src/app/product-inventory/model/product-id.model';
import { Product } from 'src/app/product-inventory/model/product.model';
import { environment } from 'src/environments/environment';
import { CartItem } from '../model/cart-item.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  cart: string = 'cart';
  product: string = 'products';
  base = environment.baseURLCart;

  constructor(private httpClient: HttpClient) { }

  getProductsInCart(userId: string): Observable<CartItem[]> {
    return this.httpClient.get<CartItem[]>(`${this.base}${environment.cartService}/${this.cart}/${userId}`);
  }

  addProductInCart(userId: string, item: CartItem): Observable<CartItem[]> {
    return this.httpClient.post<CartItem[]>(`${this.base}${environment.cartService}/${this.cart}/${userId}`, item);
  }

  updateProductInCart(userId: string, item: CartItem): Observable<CartItem[]> {
    return this.httpClient.put<CartItem[]>(`${this.base}${environment.cartService}/${this.cart}/${userId}`, item);
  }

  deleteProductFromCart(userId: string, productId: string): Observable<CartItem[]> {
    return this.httpClient.delete<CartItem[]>(`${this.base}${environment.cartService}/${this.cart}/${userId}/${productId}`);
  }

  getProductsForCartItems(productIds: ProductId[]): Observable<Product[]> {
    return this.httpClient.post<Product[]>(`${this.base}${environment.inventoryService}/${this.product}`, productIds);
  }

  clearUserCart(userId: string): Observable<any> {
    return this.httpClient.put<any>(`${this.base}${environment.cartService}/${this.cart}/clear/${userId}`, {});
  }
}

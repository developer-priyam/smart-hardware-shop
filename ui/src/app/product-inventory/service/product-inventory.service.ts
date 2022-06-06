import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductInventoryService {
  product: string = 'product';
  allProduct: string = 'products';
  banner: string = 'productHighlight';
  search: string = 'search';

  constructor(private httpClient: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${environment.baseURL}${environment.inventoryService}${this.allProduct}`);
  }

  addProduct(product: Product): Observable<any> {
    return this.httpClient.post(`${environment.baseURL}${environment.inventoryService}${this.product}`, product);
  }

  updateProduct(product: Product): Observable<any> {
    return this.httpClient.put(`${environment.baseURL}${environment.inventoryService}${this.product}`, product);
  }

  deleteProduct(product: Product): Observable<any> {
    return this.httpClient.delete(`${environment.baseURL}${environment.inventoryService}${this.product}/${product.productId}`);
  }

  getBanner(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${environment.baseURL}${environment.inventoryService}${this.banner}`);
  }

  searchProducts(value: {text: string}): Observable<Product[]> {
    return this.httpClient.post<Product[]>(`${environment.baseURL}${environment.inventoryService}${this.search}`, value);
  }
}

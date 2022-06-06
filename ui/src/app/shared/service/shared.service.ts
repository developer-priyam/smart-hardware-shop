import { Injectable, EventEmitter } from '@angular/core';
import { Product } from 'src/app/product-inventory/model/product.model';
import { User } from 'src/app/user/model/user.model';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  addToCart = new EventEmitter<Product>();
  userFetched = new EventEmitter<User>();
  constructor() {}
}

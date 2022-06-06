import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { ProductDescription } from 'src/app/product-inventory/model/product-description.model';
import { ProductId } from 'src/app/product-inventory/model/product-id.model';
import { Product } from 'src/app/product-inventory/model/product.model';
import { CartItem } from '../model/cart-item.model';
import { ShoppingCartService } from '../service/shopping-cart.service';

import { ProductListComponent } from './product-list.component';

describe('ProductListComponent', () => {
  let component: ProductListComponent;
  let fixture: ComponentFixture<ProductListComponent>;
  let service: ShoppingCartService;
  const cartItems: CartItem[] = [
    {
      productId: "6affa395-91a9-465a-be13-6d7d023087d",
      quantity: 5
    },
    {
      productId: "6affa395-91a9-4f6a-be13-6d7d023087d",
      quantity: 1
    },
    {
      productId: "6aff6695-91a9-465a-be13-6d7d023087d",
      quantity: 1
    }
  ];

  const productId1: ProductId = {
    id: "6aff6695-91a9-465a-be13-6d7d023087d"
  };
  const description1: ProductDescription = {
    availableCount: 5,
    categoryId: 1,
    isHighlighted: true,
    name: 'drill machine',
    price: 10.5
  };
  const productId2: ProductId = {
    id: "6affa395-91a9-4f6a-be13-6d7d023087d"
  };
  const description2: ProductDescription = {
    availableCount: 3,
    categoryId: 1,
    isHighlighted: false,
    name: 'screwdriver',
    price: 175
  };
  const productId3: ProductId = {
    id: "6affa395-91a9-465a-be13-6d7d023087d"
  };
  const description3: ProductDescription = {
    availableCount: 1,
    categoryId: 1,
    isHighlighted: false,
    name: 'screwdriver',
    price: 100
  };
  const productList: Product[] = [
    { description: description1, productId: productId1 }, 
    { description: description2, productId: productId2 },
    { description: description3, productId: productId3 }
  ];
  

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductListComponent ],
      providers: [{provide: service, useClass: ShoppingCartService}],
      imports: [
        HttpClientTestingModule,
        MatSnackBarModule,
        BrowserAnimationsModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get stored Shopping Cart Items', () => {
    const userId =  "6affa395-91a9-465a-be13-6d7d023087d"
    const serviceSpy = spyOn(ShoppingCartService.prototype, 'getProductsInCart')
                       .and.returnValue(of(cartItems));
    component.productList = productList;
    component.getCartItems(userId);
    expect(serviceSpy).toHaveBeenCalled();
    expect(component.cartItems.length).toEqual(3);
    expect(component.productList.length).toEqual(3);
  });

  it('should be able to add product to the shopping cart', () => {
    const userId =  "6affa395-91a9-465a-be13-6d7d023087d"
    const serviceSpy = spyOn(ShoppingCartService.prototype, 'addProductInCart')
                       .and.returnValue(of(cartItems));
    const productId: ProductId = {
      id: "6affa395-91a9-465a-be13-6d7d0230s7d"
    };
    const description: ProductDescription = {
      availableCount: 4,
      categoryId: 2,
      isHighlighted: false,
      name: 'Bolts',
      price: 2
    };
    const prouct:Product = {description, productId};
    component.addItemToCart(prouct);
    expect(serviceSpy).toHaveBeenCalled();
    expect(component.cartItems.length).toEqual(1);
    expect(component.productList.length).toEqual(1);
  });

  it('should get totalp price for cart', () => {
    const userId =  "6affa395-91a9-465a-be13-6d7d023087d"
    component.productList = productList;
    expect(component.getCartTotal()).toEqual(285.5);
  });

  it('should get quantity for the cart item', () => {
    const userId =  "6affa395-91a9-465a-be13-6d7d023087d"
    component.productList = productList;
    component.cartItems = cartItems;
    expect(component.getQuantity(productId2)).toEqual(1);
  });

  it('should delete eerything in the cart', () => {
    const userId =  "6affa395-91a9-465a-be13-6d7d023087d"
    component.productList = productList;
    component.cartItems = cartItems;
    component.cleanCart();
    expect(component.cartItems.length).toEqual(0);
    expect(component.productList.length).toEqual(0);
  });

  it('should update item quantity', () => {
    const userId =  "6affa395-91a9-465a-be13-6d7d023087d"
    component.productList = productList;
    component.cartItems = cartItems;
    component.updateQuantity(productList[2], 5);
    const ci = component.cartItems.find(item => item.productId === productId2.id);
    const qty = (ci !== undefined) ? ci.quantity : 0;
    expect(qty).toEqual(1);
  });
});

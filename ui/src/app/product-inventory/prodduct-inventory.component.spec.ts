import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { of } from 'rxjs';
import { ProductDescription } from './model/product-description.model';
import { ProductId } from './model/product-id.model';
import { Product } from './model/product.model';

import { ProdductInventoryComponent } from './prodduct-inventory.component';
import { ProductInventoryService } from './service/product-inventory.service';

describe('ProdductInventoryComponent', () => {
  let component: ProdductInventoryComponent;
  let fixture: ComponentFixture<ProdductInventoryComponent>;
  let service: ProductInventoryService;
  const productId1: ProductId = {
    id: "6affa395-91a9-465a-be13-6d7d023087d"
  };
  const description1: ProductDescription = {
    availableCount: 5,
    categoryId: 1,
    isHighlighted: true,
    name: 'drill machine',
    price: 10.5
  };
  const productId2: ProductId = {
    id: "6affa395-91a9-465a-be13-6d7d023087v"
  }
  const description2: ProductDescription = {
    availableCount: 0,
    categoryId: 1,
    isHighlighted: false,
    name: 'screwdriver',
    price: 100
  };
  const productList: Product[] = [
    { description: description1, productId: productId1 }, { description: description2, productId: productId2 }
  ];

  const productName: string[] = ['test'];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdductInventoryComponent ],
      providers: [{provide: service, useClass: ProductInventoryService}],
      imports: [
        HttpClientTestingModule,
        MatSnackBarModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdductInventoryComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(ProductInventoryService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get Highlighted product list', () => {
    const serviceSpy = spyOn(ProductInventoryService.prototype, 'getBanner')
                       .and.returnValue(of([productList[0]]));
    component.getHighlightedProducts();
    expect(serviceSpy).toHaveBeenCalled();
    expect(component.highlightedProducts.length).toEqual(1);
  });

  it('should get name from product list', () => {
    component.productList = productList;
    const name: string[] = component.getProductNames();
    expect(name[0]).toEqual('drill machine');
  });

  it('should get In Sock Product Status', () => {
     const status = component.getProductStatus(productList[0]);
     expect(status).toEqual('In Stock');
  });

  it('should get Sold Out Product Status', () => {
    const status = component.getProductStatus(productList[1]);
    expect(status).toEqual('Sold Out');
 });

 it('should get complete product List', () => {
    const serviceSpy = spyOn(ProductInventoryService.prototype, 'getProducts')
                                           .and.returnValue(of(productList));
    component.getProducts();
    expect(serviceSpy).toHaveBeenCalled();
    expect(component.productList.length).toEqual(2); 
  });

  it('should get filtered product List', () => {
    const serviceSpy = spyOn(ProductInventoryService.prototype, 'searchProducts')
                                           .and.returnValue(of([productList[0]]));
    component.getFilteredProducts('drill');
    expect(serviceSpy).toHaveBeenCalled();
    expect(component.productList.length).toEqual(1); 
  });


});

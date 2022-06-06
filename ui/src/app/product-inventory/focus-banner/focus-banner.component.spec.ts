import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { of } from 'rxjs';
import { ProductDescription } from '../model/product-description.model';
import { ProductId } from '../model/product-id.model';
import { Product } from '../model/product.model';
import { ProductInventoryService } from '../service/product-inventory.service';

import { FocusBannerComponent } from './focus-banner.component';

describe('FocusBannerComponent', () => {
  let component: FocusBannerComponent;
  let fixture: ComponentFixture<FocusBannerComponent>;
  let service: ProductInventoryService;
  const productId: ProductId = {
    id: "6affa395-91a9-465a-be13-6d7d023087d"
  }
  const description: ProductDescription = {
    availableCount: 5,
    categoryId: 1,
    isHighlighted: false,
    name: 'test',
    price: 10.5
  }
  const productList: Product[] = [
    { description, productId }
  ]
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FocusBannerComponent ],
      providers: [{provide: service, useClass: ProductInventoryService}],
      imports: [
        HttpClientTestingModule,
        MatSnackBarModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FocusBannerComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(ProductInventoryService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get Banner element', () => {
    const serviceSpy = spyOn(ProductInventoryService.prototype, 'getBanner')
                       .and.returnValue(of(productList));
    component.getBanner();
    expect(serviceSpy).toHaveBeenCalled();
    expect(component.highlightedProduct.length).toEqual(1);
  });
});

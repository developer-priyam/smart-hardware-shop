import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductActionButtonComponent } from './product-action-button.component';

describe('ProductActionButtonComponent', () => {
  let component: ProductActionButtonComponent;
  let fixture: ComponentFixture<ProductActionButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductActionButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductActionButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should increase product quantity', () => {
    component.number = 1;
    component.increaseCount();
    expect(component.number).toEqual(2);
  });

  it('should descrease product quantity', () => {
    component.number = 1;
    component.decreaseCount();
    expect(component.number).toEqual(0);
  });
});

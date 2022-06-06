import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdductInventoryComponent } from './prodduct-inventory.component';

describe('ProdductInventoryComponent', () => {
  let component: ProdductInventoryComponent;
  let fixture: ComponentFixture<ProdductInventoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdductInventoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdductInventoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

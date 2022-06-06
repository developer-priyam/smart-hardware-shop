import { TestBed } from '@angular/core/testing';

import { ProductInventoryService } from './product-inventory.service';

describe('ProductInventoryService', () => {
  let service: ProductInventoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductInventoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

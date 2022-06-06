import { TestBed } from '@angular/core/testing';

import { ProductInventoryService } from './product-inventory.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ProductInventoryService', () => {
  let service: ProductInventoryService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductInventoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

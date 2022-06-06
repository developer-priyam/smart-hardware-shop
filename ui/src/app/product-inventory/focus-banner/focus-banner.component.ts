import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ChangeDetectionStrategy, Input } from '@angular/core';
import { LoggerService } from 'src/app/shared/service/logger.service';
import { Product } from '../model/product.model';
import { ProductInventoryService } from '../service/product-inventory.service';

@Component({
  selector: 'app-focus-banner',
  templateUrl: './focus-banner.component.html',
  styleUrls: ['./focus-banner.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FocusBannerComponent implements OnInit {
  @Input() highlightedProduct: Product[] = [];
  currentDisplayProduct: Product;

  constructor(private inventory: ProductInventoryService, private logger: LoggerService) { 
    this.currentDisplayProduct = {
      productId: { id: ''},
      description: {
        categoryId: '',
        name: '',
        availableCount: 0,
        price: 0,
        isHighlighted: false
      }
    };
  }

  ngOnInit(): void {
    this.getBanner();
  }

  getBanner() {
    this.inventory.getBanner()
    .subscribe(
      (response => {
        this.highlightedProduct = response;
        this.currentDisplayProduct = this.highlightedProduct[0];
      }),
      ((error: HttpErrorResponse) => this.logger.openSnackBar(error.message, 'close'))
    )
  }

}

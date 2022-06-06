import { Component, OnInit, ChangeDetectionStrategy, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class ProductComponent implements OnInit {

  @Input('name') name: string = '';
  @Input('price') price: number = 0;
  @Input('status') status: string = '';


  constructor() { }

  ngOnInit(): void {
  }

}

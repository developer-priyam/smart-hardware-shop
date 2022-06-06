import { Component, OnInit, ChangeDetectionStrategy, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-product-action-button',
  templateUrl: './product-action-button.component.html',
  styleUrls: ['./product-action-button.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductActionButtonComponent implements OnInit {
  @Input() number = 0;

  @Output() updatedCount = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  emitCurrentCount(): void {
    this.updatedCount.emit(this.number);
  }

  increaseCount(): void {
    this.number++;
    this.emitCurrentCount();
  }

  decreaseCount(): void {
    if (this.number > 0) this.number--;
    this.emitCurrentCount();
  }

}

import { Component, EventEmitter, ChangeDetectionStrategy, Input, OnChanges, SimpleChanges, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class SearchBarComponent implements OnChanges {

  searchControl = new FormControl('');

  @Input() productNames: string[] = [];

  filteredOptions!: Observable<string[]>;

  @Output() searchedText = new EventEmitter<string>(); 

  constructor() {}

  ngOnChanges(changes: SimpleChanges): void {
    this.filteredOptions = this.searchControl.valueChanges.pipe(
      startWith(''),
      map(name => this._filter(name))
    );
  }

  private _filter(value: string): string[] {
      const filterValue = value.toLowerCase();
      return this.productNames.filter(name => name.toLowerCase().includes(filterValue));
  }

  emitText(value: string) {
    this.searchedText.emit(this.searchControl.value);
  }

}

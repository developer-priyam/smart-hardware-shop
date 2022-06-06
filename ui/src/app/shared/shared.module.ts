import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { ProductComponent } from './product/product.component';
import { ProductActionButtonComponent } from './product-action-button/product-action-button.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatInputModule } from '@angular/material/input';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDialogModule} from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';

const matModules = [
  MatAutocompleteModule,
  MatFormFieldModule,
  ReactiveFormsModule,
  MatCardModule,
  MatButtonModule,
  MatGridListModule,
  MatInputModule,
  MatSnackBarModule,
  MatDialogModule,
  MatIconModule
]

@NgModule({
  declarations: [
    SearchBarComponent,
    ProductComponent,
    ProductActionButtonComponent
  ],
  imports: [
    CommonModule,
    matModules
  ],
  exports: [
    SearchBarComponent,
    ProductComponent,
    ProductActionButtonComponent,
    matModules
  ]
})
export class SharedModule { }

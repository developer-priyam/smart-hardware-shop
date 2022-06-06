import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SharedService } from '../shared/service/shared.service';
import { User } from '../user/model/user.model';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  constructor(private userService: UserService, private sharedService: SharedService) {
    this.subscription =  this.userService.getDefaultUser().subscribe((response: User) => this.sharedService.userFetched.emit(response));
   }

  ngOnInit(): void {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}

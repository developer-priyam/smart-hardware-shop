import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-confirmation-box',
  templateUrl: './confirmation-box.component.html',
  styleUrls: ['./confirmation-box.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConfirmationBoxComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

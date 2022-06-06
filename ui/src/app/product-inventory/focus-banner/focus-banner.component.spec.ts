import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FocusBannerComponent } from './focus-banner.component';

describe('FocusBannerComponent', () => {
  let component: FocusBannerComponent;
  let fixture: ComponentFixture<FocusBannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FocusBannerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FocusBannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

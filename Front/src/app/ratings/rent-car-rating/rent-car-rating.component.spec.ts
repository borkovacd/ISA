import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentCarRatingComponent } from './rent-car-rating.component';

describe('RentCarRatingComponent', () => {
  let component: RentCarRatingComponent;
  let fixture: ComponentFixture<RentCarRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentCarRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentCarRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomRatingComponent } from './room-rating.component';

describe('RoomRatingComponent', () => {
  let component: RoomRatingComponent;
  let fixture: ComponentFixture<RoomRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

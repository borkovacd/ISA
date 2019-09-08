import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RevenuesRentComponent } from './revenues-rent.component';

describe('RevenuesRentComponent', () => {
  let component: RevenuesRentComponent;
  let fixture: ComponentFixture<RevenuesRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RevenuesRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RevenuesRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

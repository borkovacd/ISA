import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PricesRentComponent } from './prices-rent.component';

describe('PricesRentComponent', () => {
  let component: PricesRentComponent;
  let fixture: ComponentFixture<PricesRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PricesRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PricesRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

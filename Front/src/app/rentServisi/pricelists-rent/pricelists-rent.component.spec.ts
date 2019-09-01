import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PricelistsRentComponent } from './pricelists-rent.component';

describe('PricelistsRentComponent', () => {
  let component: PricelistsRentComponent;
  let fixture: ComponentFixture<PricelistsRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PricelistsRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PricelistsRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

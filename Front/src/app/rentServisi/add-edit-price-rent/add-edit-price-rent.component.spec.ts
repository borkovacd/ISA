import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditPriceRentComponent } from './add-edit-price-rent.component';

describe('AddEditPriceRentComponent', () => {
  let component: AddEditPriceRentComponent;
  let fixture: ComponentFixture<AddEditPriceRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditPriceRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditPriceRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

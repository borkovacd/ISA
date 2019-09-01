import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditPricelistRentComponent } from './add-edit-pricelist-rent.component';

describe('AddEditPricelistRentComponent', () => {
  let component: AddEditPricelistRentComponent;
  let fixture: ComponentFixture<AddEditPricelistRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditPricelistRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditPricelistRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

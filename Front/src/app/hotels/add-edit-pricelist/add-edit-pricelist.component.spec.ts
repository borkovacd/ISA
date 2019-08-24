import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditPricelistComponent } from './add-edit-pricelist.component';

describe('AddEditPricelistComponent', () => {
  let component: AddEditPricelistComponent;
  let fixture: ComponentFixture<AddEditPricelistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditPricelistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditPricelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditFilijalaComponent } from './add-edit-filijala.component';

describe('AddEditFilijalaComponent', () => {
  let component: AddEditFilijalaComponent;
  let fixture: ComponentFixture<AddEditFilijalaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditFilijalaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditFilijalaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

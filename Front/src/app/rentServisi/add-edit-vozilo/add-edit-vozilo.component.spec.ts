import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditVoziloComponent } from './add-edit-vozilo.component';

describe('AddEditVoziloComponent', () => {
  let component: AddEditVoziloComponent;
  let fixture: ComponentFixture<AddEditVoziloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditVoziloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditVoziloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdditionalServiceComponent } from './add-additional-service.component';

describe('AddAdditionalServiceComponent', () => {
  let component: AddAdditionalServiceComponent;
  let fixture: ComponentFixture<AddAdditionalServiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAdditionalServiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAdditionalServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

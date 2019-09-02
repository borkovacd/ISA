import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserServisiComponent } from './registered-user-servisi.component';

describe('RegisteredUserServisiComponent', () => {
  let component: RegisteredUserServisiComponent;
  let fixture: ComponentFixture<RegisteredUserServisiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserServisiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserServisiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

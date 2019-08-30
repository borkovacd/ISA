import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserHoteliComponent } from './registered-user-hoteli.component';

describe('RegisteredUserHoteliComponent', () => {
  let component: RegisteredUserHoteliComponent;
  let fixture: ComponentFixture<RegisteredUserHoteliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserHoteliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserHoteliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

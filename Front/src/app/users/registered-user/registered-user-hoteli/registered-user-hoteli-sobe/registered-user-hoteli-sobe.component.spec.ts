import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserHoteliSobeComponent } from './registered-user-hoteli-sobe.component';

describe('RegisteredUserHoteliSobeComponent', () => {
  let component: RegisteredUserHoteliSobeComponent;
  let fixture: ComponentFixture<RegisteredUserHoteliSobeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserHoteliSobeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserHoteliSobeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

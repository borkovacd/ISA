import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserMyVozilaComponent } from './registered-user-my-vozila.component';

describe('RegisteredUserMyVozilaComponent', () => {
  let component: RegisteredUserMyVozilaComponent;
  let fixture: ComponentFixture<RegisteredUserMyVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserMyVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserMyVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

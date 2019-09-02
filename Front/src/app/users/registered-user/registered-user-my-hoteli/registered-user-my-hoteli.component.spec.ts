import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserMyHoteliComponent } from './registered-user-my-hoteli.component';

describe('RegisteredUserMyHoteliComponent', () => {
  let component: RegisteredUserMyHoteliComponent;
  let fixture: ComponentFixture<RegisteredUserMyHoteliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserMyHoteliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserMyHoteliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

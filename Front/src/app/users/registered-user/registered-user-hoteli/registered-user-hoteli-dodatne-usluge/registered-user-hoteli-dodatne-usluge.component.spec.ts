import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserHoteliDodatneUslugeComponent } from './registered-user-hoteli-dodatne-usluge.component';

describe('RegisteredUserHoteliDodatneUslugeComponent', () => {
  let component: RegisteredUserHoteliDodatneUslugeComponent;
  let fixture: ComponentFixture<RegisteredUserHoteliDodatneUslugeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserHoteliDodatneUslugeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserHoteliDodatneUslugeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

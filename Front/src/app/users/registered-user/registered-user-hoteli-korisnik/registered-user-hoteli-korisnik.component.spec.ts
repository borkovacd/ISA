import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserHoteliKorisnikComponent } from './registered-user-hoteli-korisnik.component';

describe('RegisteredUserHoteliKorisnikComponent', () => {
  let component: RegisteredUserHoteliKorisnikComponent;
  let fixture: ComponentFixture<RegisteredUserHoteliKorisnikComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserHoteliKorisnikComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserHoteliKorisnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

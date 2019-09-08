import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserVozilaKorisnikComponent } from './registered-user-vozila-korisnik.component';

describe('RegisteredUserVozilaKorisnikComponent', () => {
  let component: RegisteredUserVozilaKorisnikComponent;
  let fixture: ComponentFixture<RegisteredUserVozilaKorisnikComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserVozilaKorisnikComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserVozilaKorisnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

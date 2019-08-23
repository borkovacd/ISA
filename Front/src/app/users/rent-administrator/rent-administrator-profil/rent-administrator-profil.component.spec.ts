import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentAdministratorProfilComponent } from './rent-administrator-profil.component';

describe('RentAdministratorProfilComponent', () => {
  let component: RentAdministratorProfilComponent;
  let fixture: ComponentFixture<RentAdministratorProfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentAdministratorProfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentAdministratorProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

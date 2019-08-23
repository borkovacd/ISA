import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentAdministratorProfilPageComponent } from './rent-administrator-profil-page.component';

describe('RentAdministratorProfilPageComponent', () => {
  let component: RentAdministratorProfilPageComponent;
  let fixture: ComponentFixture<RentAdministratorProfilPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentAdministratorProfilPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentAdministratorProfilPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

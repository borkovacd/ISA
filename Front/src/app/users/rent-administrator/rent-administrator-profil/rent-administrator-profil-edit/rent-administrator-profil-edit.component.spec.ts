import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentAdministratorProfilEditComponent } from './rent-administrator-profil-edit.component';

describe('RentAdministratorProfilEditComponent', () => {
  let component: RentAdministratorProfilEditComponent;
  let fixture: ComponentFixture<RentAdministratorProfilEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentAdministratorProfilEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentAdministratorProfilEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

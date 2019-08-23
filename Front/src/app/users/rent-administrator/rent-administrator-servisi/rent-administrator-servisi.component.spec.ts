import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentAdministratorServisiComponent } from './rent-administrator-servisi.component';

describe('RentAdministratorServisiComponent', () => {
  let component: RentAdministratorServisiComponent;
  let fixture: ComponentFixture<RentAdministratorServisiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentAdministratorServisiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentAdministratorServisiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentAdministratorComponent } from './rent-administrator.component';

describe('RentAdministratorComponent', () => {
  let component: RentAdministratorComponent;
  let fixture: ComponentFixture<RentAdministratorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentAdministratorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentAdministratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

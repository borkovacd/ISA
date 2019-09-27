import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledOcenaRentComponent } from './pregled-ocena-rent.component';

describe('PregledOcenaRentComponent', () => {
  let component: PregledOcenaRentComponent;
  let fixture: ComponentFixture<PregledOcenaRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PregledOcenaRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PregledOcenaRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

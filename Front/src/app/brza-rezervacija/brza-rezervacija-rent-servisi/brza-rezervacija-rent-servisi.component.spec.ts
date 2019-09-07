import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaRentServisiComponent } from './brza-rezervacija-rent-servisi.component';

describe('BrzaRezervacijaRentServisiComponent', () => {
  let component: BrzaRezervacijaRentServisiComponent;
  let fixture: ComponentFixture<BrzaRezervacijaRentServisiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaRentServisiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaRentServisiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaRentServisiIzborComponent } from './brza-rezervacija-rent-servisi-izbor.component';

describe('BrzaRezervacijaRentServisiIzborComponent', () => {
  let component: BrzaRezervacijaRentServisiIzborComponent;
  let fixture: ComponentFixture<BrzaRezervacijaRentServisiIzborComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaRentServisiIzborComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaRentServisiIzborComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

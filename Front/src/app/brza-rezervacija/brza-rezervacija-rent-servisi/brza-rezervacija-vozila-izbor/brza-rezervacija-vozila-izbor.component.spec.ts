import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaVozilaIzborComponent } from './brza-rezervacija-vozila-izbor.component';

describe('BrzaRezervacijaVozilaIzborComponent', () => {
  let component: BrzaRezervacijaVozilaIzborComponent;
  let fixture: ComponentFixture<BrzaRezervacijaVozilaIzborComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaVozilaIzborComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaVozilaIzborComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

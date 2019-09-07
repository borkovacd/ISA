import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaHoteliIzborComponent } from './brza-rezervacija-hoteli-izbor.component';

describe('BrzaRezervacijaHoteliIzborComponent', () => {
  let component: BrzaRezervacijaHoteliIzborComponent;
  let fixture: ComponentFixture<BrzaRezervacijaHoteliIzborComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaHoteliIzborComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaHoteliIzborComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

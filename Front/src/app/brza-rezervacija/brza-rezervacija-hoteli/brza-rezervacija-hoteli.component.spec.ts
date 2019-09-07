import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaHoteliComponent } from './brza-rezervacija-hoteli.component';

describe('BrzaRezervacijaHoteliComponent', () => {
  let component: BrzaRezervacijaHoteliComponent;
  let fixture: ComponentFixture<BrzaRezervacijaHoteliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaHoteliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaHoteliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

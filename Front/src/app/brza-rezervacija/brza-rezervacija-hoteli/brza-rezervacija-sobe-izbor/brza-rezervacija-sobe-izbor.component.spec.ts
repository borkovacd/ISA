import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaSobeIzborComponent } from './brza-rezervacija-sobe-izbor.component';

describe('BrzaRezervacijaSobeIzborComponent', () => {
  let component: BrzaRezervacijaSobeIzborComponent;
  let fixture: ComponentFixture<BrzaRezervacijaSobeIzborComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaSobeIzborComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaSobeIzborComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

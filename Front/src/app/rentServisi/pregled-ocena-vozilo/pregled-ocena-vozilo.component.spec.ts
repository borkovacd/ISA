import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledOcenaVoziloComponent } from './pregled-ocena-vozilo.component';

describe('PregledOcenaVoziloComponent', () => {
  let component: PregledOcenaVoziloComponent;
  let fixture: ComponentFixture<PregledOcenaVoziloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PregledOcenaVoziloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PregledOcenaVoziloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

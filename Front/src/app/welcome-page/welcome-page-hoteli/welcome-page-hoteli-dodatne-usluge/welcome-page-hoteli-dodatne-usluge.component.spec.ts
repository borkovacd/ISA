import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageHoteliDodatneUslugeComponent } from './welcome-page-hoteli-dodatne-usluge.component';

describe('WelcomePageHoteliDodatneUslugeComponent', () => {
  let component: WelcomePageHoteliDodatneUslugeComponent;
  let fixture: ComponentFixture<WelcomePageHoteliDodatneUslugeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageHoteliDodatneUslugeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageHoteliDodatneUslugeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageServisiFilijaleComponent } from './welcome-page-servisi-filijale.component';

describe('WelcomePageServisiFilijaleComponent', () => {
  let component: WelcomePageServisiFilijaleComponent;
  let fixture: ComponentFixture<WelcomePageServisiFilijaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageServisiFilijaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageServisiFilijaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageHoteliComponent } from './welcome-page-hoteli.component';

describe('WelcomePageHoteliComponent', () => {
  let component: WelcomePageHoteliComponent;
  let fixture: ComponentFixture<WelcomePageHoteliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageHoteliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageHoteliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageHoteliSobeComponent } from './welcome-page-hoteli-sobe.component';

describe('WelcomePageHoteliSobeComponent', () => {
  let component: WelcomePageHoteliSobeComponent;
  let fixture: ComponentFixture<WelcomePageHoteliSobeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageHoteliSobeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageHoteliSobeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageHoteliCenovnikComponent } from './welcome-page-hoteli-cenovnik.component';

describe('WelcomePageHoteliCenovnikComponent', () => {
  let component: WelcomePageHoteliCenovnikComponent;
  let fixture: ComponentFixture<WelcomePageHoteliCenovnikComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageHoteliCenovnikComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageHoteliCenovnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

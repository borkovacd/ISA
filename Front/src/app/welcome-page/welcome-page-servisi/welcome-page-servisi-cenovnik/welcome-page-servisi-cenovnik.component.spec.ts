import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageServisiCenovnikComponent } from './welcome-page-servisi-cenovnik.component';

describe('WelcomePageServisiCenovnikComponent', () => {
  let component: WelcomePageServisiCenovnikComponent;
  let fixture: ComponentFixture<WelcomePageServisiCenovnikComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageServisiCenovnikComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageServisiCenovnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

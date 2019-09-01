import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageServisiComponent } from './welcome-page-servisi.component';

describe('WelcomePageServisiComponent', () => {
  let component: WelcomePageServisiComponent;
  let fixture: ComponentFixture<WelcomePageServisiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageServisiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageServisiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

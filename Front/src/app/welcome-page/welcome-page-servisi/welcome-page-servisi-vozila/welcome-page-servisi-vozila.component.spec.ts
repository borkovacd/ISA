import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomePageServisiVozilaComponent } from './welcome-page-servisi-vozila.component';

describe('WelcomePageServisiVozilaComponent', () => {
  let component: WelcomePageServisiVozilaComponent;
  let fixture: ComponentFixture<WelcomePageServisiVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePageServisiVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WelcomePageServisiVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredUserServisiVozilaComponent } from './registered-user-servisi-vozila.component';

describe('RegisteredUserServisiVozilaComponent', () => {
  let component: RegisteredUserServisiVozilaComponent;
  let fixture: ComponentFixture<RegisteredUserServisiVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredUserServisiVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredUserServisiVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

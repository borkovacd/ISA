import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoziloRatingComponent } from './vozilo-rating.component';

describe('VoziloRatingComponent', () => {
  let component: VoziloRatingComponent;
  let fixture: ComponentFixture<VoziloRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoziloRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoziloRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceGraphsRentComponent } from './attendance-graphs-rent.component';

describe('AttendanceGraphsRentComponent', () => {
  let component: AttendanceGraphsRentComponent;
  let fixture: ComponentFixture<AttendanceGraphsRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttendanceGraphsRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendanceGraphsRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

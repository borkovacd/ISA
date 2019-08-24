import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilijaleComponent } from './filijale.component';

describe('FilijaleComponent', () => {
  let component: FilijaleComponent;
  let fixture: ComponentFixture<FilijaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilijaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilijaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

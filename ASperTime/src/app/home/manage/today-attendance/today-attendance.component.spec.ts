import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodayAttendanceComponent } from './today-attendance.component';

describe('TodayAttendanceComponent', () => {
  let component: TodayAttendanceComponent;
  let fixture: ComponentFixture<TodayAttendanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TodayAttendanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodayAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

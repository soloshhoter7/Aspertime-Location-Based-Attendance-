import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingLeaveComponent } from './pending-leave.component';

describe('PendingLeaveComponent', () => {
  let component: PendingLeaveComponent;
  let fixture: ComponentFixture<PendingLeaveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingLeaveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingLeaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

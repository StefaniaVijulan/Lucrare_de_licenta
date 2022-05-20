import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorFutureAppointmentsComponent } from './doctor-future-appointments.component';

describe('DoctorFutureAppointmentsComponent', () => {
  let component: DoctorFutureAppointmentsComponent;
  let fixture: ComponentFixture<DoctorFutureAppointmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorFutureAppointmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorFutureAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

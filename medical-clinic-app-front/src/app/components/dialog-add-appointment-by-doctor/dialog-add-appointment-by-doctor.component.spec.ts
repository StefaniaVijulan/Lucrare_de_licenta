import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddAppointmentByDoctorComponent } from './dialog-add-appointment-by-doctor.component';

describe('DialogAddAppointmentByDoctorComponent', () => {
  let component: DialogAddAppointmentByDoctorComponent;
  let fixture: ComponentFixture<DialogAddAppointmentByDoctorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddAppointmentByDoctorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddAppointmentByDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

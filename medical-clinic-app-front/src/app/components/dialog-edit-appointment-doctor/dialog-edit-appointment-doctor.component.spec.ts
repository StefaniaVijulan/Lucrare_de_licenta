import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditAppointmentDoctorComponent } from './dialog-edit-appointment-doctor.component';

describe('DialogEditAppointmentDoctorComponent', () => {
  let component: DialogEditAppointmentDoctorComponent;
  let fixture: ComponentFixture<DialogEditAppointmentDoctorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEditAppointmentDoctorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEditAppointmentDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

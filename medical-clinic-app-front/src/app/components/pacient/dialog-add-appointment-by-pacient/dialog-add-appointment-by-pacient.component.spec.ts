import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddAppointmentByPacientComponent } from './dialog-add-appointment-by-pacient.component';

describe('DialogAddAppointmentByPacientComponent', () => {
  let component: DialogAddAppointmentByPacientComponent;
  let fixture: ComponentFixture<DialogAddAppointmentByPacientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddAppointmentByPacientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddAppointmentByPacientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditAppointmentComponent } from './dialog-edit-appointment.component';

describe('DialogEditAppointmentComponent', () => {
  let component: DialogEditAppointmentComponent;
  let fixture: ComponentFixture<DialogEditAppointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEditAppointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEditAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

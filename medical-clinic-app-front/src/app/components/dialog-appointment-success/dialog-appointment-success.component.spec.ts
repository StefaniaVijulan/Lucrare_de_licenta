import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAppointmentSuccessComponent } from './dialog-appointment-success.component';

describe('DialogAppointmentSuccessComponent', () => {
  let component: DialogAppointmentSuccessComponent;
  let fixture: ComponentFixture<DialogAppointmentSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAppointmentSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAppointmentSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddAppointmentsRadiologyComponent } from './dialog-add-appointments-radiology.component';

describe('DialogAddAppointmentsRadiologyComponent', () => {
  let component: DialogAddAppointmentsRadiologyComponent;
  let fixture: ComponentFixture<DialogAddAppointmentsRadiologyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddAppointmentsRadiologyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddAppointmentsRadiologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

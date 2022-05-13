import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddAppointmentsHematologyComponent } from './dialog-add-appointments-hematology.component';

describe('DialogAddAppointmentsHematologyComponent', () => {
  let component: DialogAddAppointmentsHematologyComponent;
  let fixture: ComponentFixture<DialogAddAppointmentsHematologyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddAppointmentsHematologyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddAppointmentsHematologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

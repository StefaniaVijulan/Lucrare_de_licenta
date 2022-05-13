import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddAppointmentsHrComponent } from './dialog-add-appointments-hr.component';

describe('DialogAddAppointmentsHrComponent', () => {
  let component: DialogAddAppointmentsHrComponent;
  let fixture: ComponentFixture<DialogAddAppointmentsHrComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddAppointmentsHrComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddAppointmentsHrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

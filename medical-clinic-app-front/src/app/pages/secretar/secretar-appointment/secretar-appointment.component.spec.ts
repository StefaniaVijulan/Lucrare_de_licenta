import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecretarAppointmentComponent } from './secretar-appointment.component';

describe('SecretarAppointmentComponent', () => {
  let component: SecretarAppointmentComponent;
  let fixture: ComponentFixture<SecretarAppointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecretarAppointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecretarAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

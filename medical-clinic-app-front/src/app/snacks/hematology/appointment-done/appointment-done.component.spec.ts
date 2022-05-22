import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentDoneComponent } from './appointment-done.component';

describe('AppointmentDoneComponent', () => {
  let component: AppointmentDoneComponent;
  let fixture: ComponentFixture<AppointmentDoneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppointmentDoneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentDoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

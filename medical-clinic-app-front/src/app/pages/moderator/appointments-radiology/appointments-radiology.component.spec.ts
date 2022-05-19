import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentsRadiologyComponent } from './appointments-radiology.component';

describe('AppointmentsRadiologyComponent', () => {
  let component: AppointmentsRadiologyComponent;
  let fixture: ComponentFixture<AppointmentsRadiologyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppointmentsRadiologyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentsRadiologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentsHematologyComponent } from './appointments-hematology.component';

describe('AppointmentsHematologyComponent', () => {
  let component: AppointmentsHematologyComponent;
  let fixture: ComponentFixture<AppointmentsHematologyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppointmentsHematologyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentsHematologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

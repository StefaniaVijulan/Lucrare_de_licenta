import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorProgramariComponent } from './doctor-programari.component';

describe('DoctorProgramariComponent', () => {
  let component: DoctorProgramariComponent;
  let fixture: ComponentFixture<DoctorProgramariComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorProgramariComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorProgramariComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

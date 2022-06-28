import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PacientAllDoctorsComponent } from './pacient-all-doctors.component';

describe('PacientAllDoctorsComponent', () => {
  let component: PacientAllDoctorsComponent;
  let fixture: ComponentFixture<PacientAllDoctorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PacientAllDoctorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PacientAllDoctorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

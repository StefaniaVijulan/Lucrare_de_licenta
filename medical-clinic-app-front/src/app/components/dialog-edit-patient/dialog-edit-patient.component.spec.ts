import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditPatientComponent } from './dialog-edit-patient.component';

describe('DialogEditPatientComponent', () => {
  let component: DialogEditPatientComponent;
  let fixture: ComponentFixture<DialogEditPatientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEditPatientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEditPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddPacientComponent } from './dialog-add-pacient.component';

describe('DialogAddPacientComponent', () => {
  let component: DialogAddPacientComponent;
  let fixture: ComponentFixture<DialogAddPacientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddPacientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddPacientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

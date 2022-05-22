import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddResultHematologyComponent } from './dialog-add-result-hematology.component';

describe('DialogAddResultHematologyComponent', () => {
  let component: DialogAddResultHematologyComponent;
  let fixture: ComponentFixture<DialogAddResultHematologyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddResultHematologyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddResultHematologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

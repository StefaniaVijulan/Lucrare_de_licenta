import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAddResultRadiologyComponent } from './dialog-add-result-radiology.component';

describe('DialogAddResultRadiologyComponent', () => {
  let component: DialogAddResultRadiologyComponent;
  let fixture: ComponentFixture<DialogAddResultRadiologyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAddResultRadiologyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAddResultRadiologyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

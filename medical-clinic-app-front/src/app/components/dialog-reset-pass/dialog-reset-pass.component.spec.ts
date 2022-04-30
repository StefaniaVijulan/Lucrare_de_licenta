import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogResetPassComponent } from './dialog-reset-pass.component';

describe('DialogResetPassComponent', () => {
  let component: DialogResetPassComponent;
  let fixture: ComponentFixture<DialogResetPassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogResetPassComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogResetPassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

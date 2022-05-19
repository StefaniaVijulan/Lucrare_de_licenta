import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogCancelDeleteUserComponent } from './dialog-cancel-delete-user.component';

describe('DialogCancelDeleteUserComponent', () => {
  let component: DialogCancelDeleteUserComponent;
  let fixture: ComponentFixture<DialogCancelDeleteUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogCancelDeleteUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogCancelDeleteUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEdituserComponent } from './dialog-edituser.component';

describe('DialogEdituserComponent', () => {
  let component: DialogEdituserComponent;
  let fixture: ComponentFixture<DialogEdituserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEdituserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEdituserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

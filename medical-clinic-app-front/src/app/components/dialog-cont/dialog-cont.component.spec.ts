import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogContComponent } from './dialog-cont.component';

describe('DialogContComponent', () => {
  let component: DialogContComponent;
  let fixture: ComponentFixture<DialogContComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogContComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogContComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditFisaComponent } from './dialog-edit-fisa.component';

describe('DialogEditFisaComponent', () => {
  let component: DialogEditFisaComponent;
  let fixture: ComponentFixture<DialogEditFisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEditFisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEditFisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

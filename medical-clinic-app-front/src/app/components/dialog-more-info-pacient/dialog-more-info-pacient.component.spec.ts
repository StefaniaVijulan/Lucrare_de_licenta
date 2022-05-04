import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogMoreInfoPacientComponent } from './dialog-more-info-pacient.component';

describe('DialogMoreInfoPacientComponent', () => {
  let component: DialogMoreInfoPacientComponent;
  let fixture: ComponentFixture<DialogMoreInfoPacientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogMoreInfoPacientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogMoreInfoPacientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

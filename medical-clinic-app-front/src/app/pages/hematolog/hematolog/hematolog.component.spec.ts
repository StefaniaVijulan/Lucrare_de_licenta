import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HematologComponent } from './hematolog.component';

describe('HematologComponent', () => {
  let component: HematologComponent;
  let fixture: ComponentFixture<HematologComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HematologComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HematologComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

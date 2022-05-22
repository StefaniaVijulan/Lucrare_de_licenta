import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HematologResultComponent } from './hematolog-result.component';

describe('HematologResultComponent', () => {
  let component: HematologResultComponent;
  let fixture: ComponentFixture<HematologResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HematologResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HematologResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

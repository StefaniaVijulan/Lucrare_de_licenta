import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HematologAllResultComponent } from './hematolog-all-result.component';

describe('HematologAllResultComponent', () => {
  let component: HematologAllResultComponent;
  let fixture: ComponentFixture<HematologAllResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HematologAllResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HematologAllResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

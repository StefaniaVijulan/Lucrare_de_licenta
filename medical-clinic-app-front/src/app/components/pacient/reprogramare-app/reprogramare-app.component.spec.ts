import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReprogramareAppComponent } from './reprogramare-app.component';

describe('ReprogramareAppComponent', () => {
  let component: ReprogramareAppComponent;
  let fixture: ComponentFixture<ReprogramareAppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReprogramareAppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReprogramareAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

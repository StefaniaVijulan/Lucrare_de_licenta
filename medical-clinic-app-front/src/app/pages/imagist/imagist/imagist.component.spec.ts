import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagistComponent } from './imagist.component';

describe('ImagistComponent', () => {
  let component: ImagistComponent;
  let fixture: ComponentFixture<ImagistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImagistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImagistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

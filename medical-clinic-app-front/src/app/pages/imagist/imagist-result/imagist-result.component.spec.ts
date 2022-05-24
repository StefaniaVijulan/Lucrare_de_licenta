import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagistResultComponent } from './imagist-result.component';

describe('ImagistResultComponent', () => {
  let component: ImagistResultComponent;
  let fixture: ComponentFixture<ImagistResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImagistResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImagistResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

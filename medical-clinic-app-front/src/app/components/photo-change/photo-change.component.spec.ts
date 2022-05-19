import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhotoChangeComponent } from './photo-change.component';

describe('PhotoChangeComponent', () => {
  let component: PhotoChangeComponent;
  let fixture: ComponentFixture<PhotoChangeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PhotoChangeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PhotoChangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeratorImagistComponent } from './moderator-imagist.component';

describe('ModeratorImagistComponent', () => {
  let component: ModeratorImagistComponent;
  let fixture: ComponentFixture<ModeratorImagistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModeratorImagistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeratorImagistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

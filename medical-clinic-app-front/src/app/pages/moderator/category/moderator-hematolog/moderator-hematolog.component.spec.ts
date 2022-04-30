import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeratorHematologComponent } from './moderator-hematolog.component';

describe('ModeratorHematologComponent', () => {
  let component: ModeratorHematologComponent;
  let fixture: ComponentFixture<ModeratorHematologComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModeratorHematologComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeratorHematologComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

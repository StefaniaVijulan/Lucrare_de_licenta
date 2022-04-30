import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeratorCardiologComponent } from './moderator-cardiolog.component';

describe('ModeratorCardiologComponent', () => {
  let component: ModeratorCardiologComponent;
  let fixture: ComponentFixture<ModeratorCardiologComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModeratorCardiologComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeratorCardiologComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

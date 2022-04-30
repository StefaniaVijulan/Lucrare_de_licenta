import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeratorSecretarComponent } from './moderator-secretar.component';

describe('ModeratorSecretarComponent', () => {
  let component: ModeratorSecretarComponent;
  let fixture: ComponentFixture<ModeratorSecretarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModeratorSecretarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeratorSecretarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

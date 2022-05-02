import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecretarComponent } from './secretar.component';

describe('SecretarComponent', () => {
  let component: SecretarComponent;
  let fixture: ComponentFixture<SecretarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecretarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecretarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

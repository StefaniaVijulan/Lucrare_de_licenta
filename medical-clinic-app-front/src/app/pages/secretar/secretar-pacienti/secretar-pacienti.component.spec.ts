import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecretarPacientiComponent } from './secretar-pacienti.component';

describe('SecretarPacientiComponent', () => {
  let component: SecretarPacientiComponent;
  let fixture: ComponentFixture<SecretarPacientiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecretarPacientiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecretarPacientiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

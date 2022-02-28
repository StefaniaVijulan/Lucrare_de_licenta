import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginPacientComponent } from './login-pacient.component';

describe('LoginPacientComponent', () => {
  let component: LoginPacientComponent;
  let fixture: ComponentFixture<LoginPacientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginPacientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginPacientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { TestBed, async, inject } from '@angular/core/testing';

import { SecretarGuard } from './secretar.guard';

describe('SecretarGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SecretarGuard]
    });
  });

  it('should ...', inject([SecretarGuard], (guard: SecretarGuard) => {
    expect(guard).toBeTruthy();
  }));
});

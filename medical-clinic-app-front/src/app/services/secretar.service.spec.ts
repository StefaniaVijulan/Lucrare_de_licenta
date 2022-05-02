import { TestBed } from '@angular/core/testing';

import { SecretarService } from './secretar.service';

describe('SecretarService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SecretarService = TestBed.get(SecretarService);
    expect(service).toBeTruthy();
  });
});

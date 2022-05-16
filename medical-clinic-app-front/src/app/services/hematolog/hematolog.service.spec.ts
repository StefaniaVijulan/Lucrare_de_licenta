import { TestBed } from '@angular/core/testing';

import { HematologService } from './hematolog.service';

describe('HematologService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HematologService = TestBed.get(HematologService);
    expect(service).toBeTruthy();
  });
});

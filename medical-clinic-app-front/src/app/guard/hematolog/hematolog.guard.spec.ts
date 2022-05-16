import { TestBed, async, inject } from '@angular/core/testing';

import { HematologGuard } from './hematolog.guard';

describe('HematologGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HematologGuard]
    });
  });

  it('should ...', inject([HematologGuard], (guard: HematologGuard) => {
    expect(guard).toBeTruthy();
  }));
});

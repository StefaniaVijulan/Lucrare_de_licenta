import { TestBed, async, inject } from '@angular/core/testing';

import { ImagistGuard } from './imagist.guard';

describe('ImagistGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ImagistGuard]
    });
  });

  it('should ...', inject([ImagistGuard], (guard: ImagistGuard) => {
    expect(guard).toBeTruthy();
  }));
});

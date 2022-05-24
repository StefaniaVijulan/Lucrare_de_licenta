import { TestBed } from '@angular/core/testing';

import { ImagistService } from './imagist.service';

describe('ImagistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ImagistService = TestBed.get(ImagistService);
    expect(service).toBeTruthy();
  });
});

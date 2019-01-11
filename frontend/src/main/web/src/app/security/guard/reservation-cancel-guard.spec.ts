import { TestBed, async, inject } from '@angular/core/testing';

import { ReservationCancelGuard } from './reservation-cancel-guard.service';

describe('ReservationCancelGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReservationCancelGuard]
    });
  });

  it('should ...', inject([ReservationCancelGuard], (guard: ReservationCancelGuard) => {
    expect(guard).toBeTruthy();
  }));
});

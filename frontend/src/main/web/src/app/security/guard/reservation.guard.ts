import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationGuard implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    return this.didRequestComeFromCorrectComponent();
  }

  didRequestComeFromCorrectComponent(): boolean {
    const correctComponentUrl: string = '/repertuar';

    if (correctComponentUrl != this.router.url) {
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }
}

import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {MovieReserveComponent} from "../../movie/movie-reserve/movie-reserve.component";

@Injectable({
  providedIn: 'root'
})
export class ReservationCancelGuard implements CanDeactivate<MovieReserveComponent> {

  constructor() {

  }

  canDeactivate(component: MovieReserveComponent, currentRoute: ActivatedRouteSnapshot,
                currentState: RouterStateSnapshot, nextState?: RouterStateSnapshot)
    : Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (nextState.url != '/repertuar/' + component.show.id + '/rezerwacja/sukces') {
      component.releaseSeats();
    }

    return true;
  }

}

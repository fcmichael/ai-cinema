import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Show} from "./show";
import {Observable} from "rxjs";
import {ReservationForm} from "./movie-reserve/movie-reserve-form/reservation-form";
import {SHOWS_URL} from "../url-config";

@Injectable({
  providedIn: 'root'
})
export class ShowService {

  constructor(private httpClient: HttpClient) {
  }

  getShow(id: number): Observable<Show> {
    return this.httpClient.get<Show>(SHOWS_URL + '/' + id);
  }

  makeReservation(id: number, form: ReservationForm) : Observable<void>{
    let url = SHOWS_URL + "/" + id + "/reservations";
    return this.httpClient.post<void>(url, form);
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Show} from "./show";
import {Observable} from "rxjs";
import {ReservationForm} from "./movie-reserve/movie-reserve-form/reservation-form";

@Injectable({
  providedIn: 'root'
})
export class ShowService {

  private showUrl: string = 'http://localhost:8080/shows';

  constructor(private httpClient: HttpClient) {
  }

  getShow(id: number): Observable<Show> {
    return this.httpClient.get<Show>(this.showUrl + '/' + id);
  }

  makeReservation(id: number, form: ReservationForm) : Observable<void>{
    let url = this.showUrl + "/" + id + "/reservations";
    return this.httpClient.post<void>(url, form);
  }
}

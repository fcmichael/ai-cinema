import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Show} from "./show";
import {Observable} from "rxjs";

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

  getReservedSeatsForShow(id: number): Observable<string[]> {
    return this.httpClient.get<string[]>(this.showUrl + '/' + id + '/reservedSeats');
  }
}

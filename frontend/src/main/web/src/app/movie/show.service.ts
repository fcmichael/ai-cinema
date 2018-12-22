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
    let path = '';

    if (id != null) {
      path = '/' + id;
    }

    return this.httpClient.get<Show>(this.showUrl + path);
  }
}

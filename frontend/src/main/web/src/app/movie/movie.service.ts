import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "./movie";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private movieUrl: string = 'http://localhost:8080/programme';

  constructor(private httpClient: HttpClient) {
  }

  getMovies(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(this.movieUrl);
  }
}

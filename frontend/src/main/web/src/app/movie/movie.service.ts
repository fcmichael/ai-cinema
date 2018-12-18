import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "./movie";
import {map} from "rxjs/operators";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private movieUrl: string = 'http://localhost:8080/programme';

  constructor(private httpClient: HttpClient, private sanitizer: DomSanitizer) {
  }

  getMoviesByGenreAndCountryAndReleaseYearAndDay(
    genre: string,
    country: string,
    releaseYear: string,
    date: string
  ): Observable<Movie[]> {

    let params = new HttpParams().append('date', date);

    if (genre != null) {
      params = params.append('genre', genre);
    }

    if (country != null) {
      params = params.append('country', country);
    }

    if (releaseYear != null) {
      params = params.append('releaseYear', releaseYear);
    }

    return this.httpClient.get<Movie[]>(this.movieUrl, {params: params})
      .pipe(map((movies: Movie[]) => {
        return movies.map(r => {
          return new Movie(
            r.id,
            r.title,
            r.genre,
            r.ageLimit,
            r.duration,
            r.releaseYear,
            r.country,
            r.description,
            this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + r.image),
            r.shows
          )
        });
      }));
  }
}

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

  private programmeUrl: string = 'http://localhost:8080/programme';
  private movieUrl: string = 'http://localhost:8080/movies';

  constructor(private httpClient: HttpClient, private sanitizer: DomSanitizer) {
  }

  getAllMovies(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(this.movieUrl)
      .pipe(map((movies: Movie[]) => {
        return movies.map(r => {
          return this.mapMovie(r);
        });
      }));
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

    return this.httpClient.get<Movie[]>(this.programmeUrl, {params: params})
      .pipe(map((movies: Movie[]) => {
        return movies.map(r => {
          return this.mapMovie(r);
        });
      }));
  }

  private mapMovie(movie: Movie): Movie {
    return new Movie(
      movie.id,
      movie.title,
      movie.genre,
      movie.ageLimit,
      movie.duration,
      movie.releaseYear,
      movie.country,
      movie.description,
      this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + movie.image),
      movie.shows
    )
  }
}

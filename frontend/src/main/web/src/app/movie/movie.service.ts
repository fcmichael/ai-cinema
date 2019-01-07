import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "./movie";
import {map} from "rxjs/operators";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

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

  getMovieById(id: number): Observable<Movie> {
    const url = this.movieUrl + '/' + id;
    return this.httpClient.get<Movie>(url)
      .pipe(map((movie: Movie) => {
        return this.mapMovie(movie);
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

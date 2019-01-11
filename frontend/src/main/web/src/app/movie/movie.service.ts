import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "./movie";
import {map} from "rxjs/operators";
import {DomSanitizer} from "@angular/platform-browser";
import {MovieForm} from "./movie-form";
import {MOVIES_URL} from "../url-config";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private httpClient: HttpClient, private sanitizer: DomSanitizer) {
  }

  getAllMovies(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(MOVIES_URL)
      .pipe(map((movies: Movie[]) => {
        return movies.map(r => {
          return this.mapMovie(r);
        });
      }));
  }

  getMovieById(id: number): Observable<Movie> {
    const url = MOVIES_URL + '/' + id;
    return this.httpClient.get<Movie>(url)
      .pipe(map((movie: Movie) => {
        return this.mapMovie(movie);
      }));
  }

  addMovie(form: MovieForm): Observable<Movie> {
    const formData: FormData = new FormData();
    formData.append('file', form.image);
    formData.append('form', JSON.stringify(form));

    return this.httpClient.post<Movie>(MOVIES_URL, formData);
  }

  editMovie(id: number, form: MovieForm): Observable<Movie> {
    const url = MOVIES_URL + '/' + id;
    return this.httpClient.put<Movie>(url, form);
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

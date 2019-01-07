import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {DomSanitizer} from "@angular/platform-browser";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {ProgrammeMovie} from "./programme-movie";

@Injectable({
  providedIn: 'root'
})
export class ProgrammeService {

  private programmeUrl: string = 'http://localhost:8080/programmes';

  constructor(private httpClient: HttpClient, private sanitizer: DomSanitizer) {
  }

  getProgrammeOfSpecificDayByMovieGenreAndCountryAndReleaseYear(
    date: string,
    genre: string,
    country: string,
    releaseYear: string
  ): Observable<ProgrammeMovie[]> {

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

    return this.httpClient.get<ProgrammeMovie[]>(this.programmeUrl, {params: params})
      .pipe(map((movies: ProgrammeMovie[]) => {
        return movies.map(r => {
          return this.mapMovie(r);
        });
      }));
  }

  private mapMovie(movie: ProgrammeMovie): ProgrammeMovie {
    return new ProgrammeMovie(
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

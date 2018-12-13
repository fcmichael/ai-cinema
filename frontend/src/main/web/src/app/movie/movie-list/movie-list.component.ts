import {Component, OnInit} from '@angular/core';
import {Movie} from "../movie";
import {MovieService} from "../movie.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  genres: string[] = ['Dramat', 'Animowany'];
  countries: string[] = ['USA', 'Niemcy', 'Francja'];
  releaseYears: number[] = [1994, 1999, 2018];
  movies: Movie[] = [];

  constructor(private movieService: MovieService, private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.movieService.getMovies().subscribe(
      resp => {
        resp.forEach(r => {
          this.movies.push(
            new Movie(
              r.id,
              r.title,
              r.genre,
              r.ageLimit,
              r.duration,
              r.releaseYear,
              r.description,
              this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + r.image)
            )
          );
        })
      }
    );
  }

}

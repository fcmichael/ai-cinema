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
              this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + r.image)
            )
          );
        })
      }
    );
  }

}

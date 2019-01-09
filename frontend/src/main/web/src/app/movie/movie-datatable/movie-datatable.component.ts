import {Component, OnInit} from '@angular/core';
import {MovieService} from "../movie.service";
import {Movie} from "../movie";
import {Genre} from "../genre";
import {Country} from "../contry";
import {AgeLimit} from "../age-limit";

@Component({
  selector: 'app-movie-datatable',
  templateUrl: './movie-datatable.component.html',
  styleUrls: ['./movie-datatable.component.css']
})
export class MovieDatatableComponent implements OnInit {

  movies: Movie[];
  displayedColumns: string[] = ['id', 'title', 'genre', 'ageLimit', 'duration', 'releaseYear',
    'country', 'edit'];

  genres = Genre;
  countries = Country;
  ageLimits = AgeLimit;

  constructor(private movieService: MovieService) {
  }

  ngOnInit() {
    this.movieService.getAllMovies().subscribe(movies => this.movies = movies);
  }
}

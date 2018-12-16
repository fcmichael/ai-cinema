import {Component, OnInit} from '@angular/core';
import {Movie} from "../movie";
import {MovieService} from "../movie.service";
import {Genre} from "../genre";
import {Country} from "../contry";
import {Weekday} from "./weekday";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  genres = Genre;
  genreKeys: string[];
  countries = Country;
  countryKeys: string[];
  releaseYears: number[];
  movies: Movie[] = [];
  displayHours: string[] = ['13:00', '18:15'];
  weekdays = Weekday;
  weekdayKeys: string[];

  selectedGenre: string;
  selectedCountry: string;
  selectedYear: string;
  selectedWeekday: string;

  constructor(private movieService: MovieService) {
  }

  ngOnInit() {
    this.genreKeys = Object.keys(Genre);
    this.countryKeys = Object.keys(Country);
    this.weekdayKeys = Object.keys(Weekday);
    this.selectedWeekday = this.weekdayKeys[(new Date).getDay()];
    this.generateReleaseYears();
    this.getMovies();
  }

  getMovies() {
    this.movieService
      .getMoviesByGenreAndCountryAndReleaseYear(this.selectedGenre, this.selectedCountry, this.selectedYear)
      .subscribe(movies => this.movies = movies);
  }

  generateReleaseYears() {
    let maxYear = 2018;
    let minYear = 1957;
    let years: number[] = [];
    for (let i = maxYear; i >= minYear; i--) {
      years.push(i);
    }

    this.releaseYears = years;
  }
}

import {Component, OnInit} from '@angular/core';
import {Genre} from "../movie/genre";
import {Country} from "../movie/contry";
import {Weekday} from "./weekday";
import {ProgrammeService} from "./programme.service";
import {ProgrammeMovie} from "./programme-movie";

@Component({
  selector: 'app-programme',
  templateUrl: './programme.component.html',
  styleUrls: ['./programme.component.css']
})
export class ProgrammeComponent implements OnInit {

  genres = Genre;
  genreKeys: string[];
  countries = Country;
  countryKeys: string[];
  releaseYears: number[];
  movies: ProgrammeMovie[] = [];
  weekdays = Weekday;
  weekdayKeys: string[];

  selectedGenre: string;
  selectedCountry: string;
  selectedYear: string;
  selectedWeekday: number;

  constructor(private programmeService: ProgrammeService) {
  }

  ngOnInit() {
    this.genreKeys = Object.keys(Genre);
    this.countryKeys = Object.keys(Country);
    this.weekdayKeys = Object.keys(Weekday);
    this.selectedWeekday = (new Date).getDay();
    this.generateReleaseYears();
    this.getMovies();
  }

  getMovies() {
    this.programmeService
      .getProgrammeOfSpecificDayByMovieGenreAndCountryAndReleaseYear(
        this.getDateFromWeekday(this.selectedWeekday), this.selectedGenre, this.selectedCountry, this.selectedYear)
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

  private getDateFromWeekday(weekday: number): string {
    let date = new Date();
    date.setDate(date.getDate() + (weekday + (7 - date.getDay())) % 7);

    return date.toISOString().substr(0, 10);
  }
}

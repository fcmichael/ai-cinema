import {Component, OnInit} from '@angular/core';
import {MovieForm} from "../movie-form";
import {Genre} from "../genre";
import {Country} from "../contry";
import {AgeLimit} from "../age-limit";
import {MovieService} from "../movie.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit {

  form: MovieForm = new MovieForm('', Object.keys(Genre)[0], Object.keys(AgeLimit)[0], 0
    , 2019, Object.keys(Country)[0], '', null);

  constructor(private movieService: MovieService, private router: Router) {
  }

  ngOnInit() {
  }

  addMovie(form: MovieForm) {
    this.movieService.addMovie(form).subscribe(
      () => this.router.navigate(['/admin/filmy'])
    );
  }

}

import {Component, OnInit} from '@angular/core';
import {MovieService} from "../movie.service";
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {Movie} from "../movie";

@Component({
  selector: 'app-movie-edit',
  templateUrl: './movie-edit.component.html',
  styleUrls: ['./movie-edit.component.css']
})
export class MovieEditComponent implements OnInit {

  movie: Movie;

  constructor(private movieService: MovieService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getMovieById();
  }

  getMovieById() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.movieService.getMovieById(parseInt(params.get('id')));
      })
    ).subscribe(movie => {
      this.movie = movie;
    });
  }

}

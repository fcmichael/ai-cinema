import {Component, OnInit} from '@angular/core';
import {MovieService} from "../movie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {Movie} from "../movie";
import {MovieForm} from "../movie-form";

@Component({
  selector: 'app-movie-edit',
  templateUrl: './movie-edit.component.html',
  styleUrls: ['./movie-edit.component.css']
})
export class MovieEditComponent implements OnInit {

  movieId: number;
  form: MovieForm;

  constructor(private movieService: MovieService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.getMovieById();
  }

  getMovieById() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.movieService.getMovieById(parseInt(params.get('id')));
      })
    ).subscribe(movie => {
      this.movieId = movie.id;
      this.form = this.mapToForm(movie);
    });
  }

  editMovie(form: MovieForm) {
    this.movieService.editMovie(this.movieId, form).subscribe(
      () => this.router.navigate(['/admin/filmy'])
    );

  }

  private mapToForm(movie: Movie): MovieForm {
    return new MovieForm(
      movie.title,
      movie.genre,
      movie.ageLimit,
      movie.duration,
      movie.releaseYear,
      movie.country,
      movie.description,
      movie.image
    );
  }

}

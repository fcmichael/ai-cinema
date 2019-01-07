import {Component, Input, OnInit} from '@angular/core';
import {Movie} from "../movie";

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.css']
})
export class MovieFormComponent implements OnInit {

  @Input() formTitle: string;
  @Input() movie: Movie;

  constructor() {
  }

  ngOnInit() {
  }

  onSubmit(){
    alert(JSON.stringify(this.movie));
  }
}

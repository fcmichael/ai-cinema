import {Component, OnInit} from '@angular/core';
import {Movie} from "../movie";
import {Genre} from "../genre";
import {Country} from "../contry";

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit {

  movie: Movie = new Movie(-1, '', Genre.ANIMOWANY, '', 1
    , 1, Country.POLAND, '', '', []);

  constructor() { }

  ngOnInit() {
  }

}

import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MovieForm} from "../movie-form";
import {Genre} from "../genre";
import {Country} from "../contry";
import {AgeLimit} from "../age-limit";

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.css']
})
export class MovieFormComponent implements OnInit {

  @Output() formSubmitEvent = new EventEmitter();
  @Input() formTitle: string;
  @Input() formButtonName: string;
  @Input() form: MovieForm;
  @Input() showImageInput: boolean;
  url;

  genres = Genre;
  genreKeys: string[];
  countries = Country;
  countryKeys: string[];
  ageLimits = AgeLimit;
  ageLimitKeys: string[];

  constructor() {
  }

  ngOnInit() {
    this.genreKeys = Object.keys(Genre);
    this.countryKeys = Object.keys(Country);
    this.ageLimitKeys = Object.keys(AgeLimit);
  }

  onSubmit() {
    this.formSubmitEvent.next(this.form);
  }

  selectFile(event) {
    let file: File = event.target.files.item(0);
    if (file.type.match('image.*')) {
      this.form.image = file;
      this.displayImage();
    }
  }

  displayImage() {
    if (this.form.image) {
      let reader = new FileReader();

      reader.onload = (event: ProgressEvent) => {
        this.url = (<FileReader>event.target).result;
      };

      reader.readAsDataURL(this.form.image);
    }
  }

}

import {Component, Input, OnInit} from '@angular/core';
import {ReservationForm} from "./reservation-form";

@Component({
  selector: 'app-movie-reserve-form',
  templateUrl: './movie-reserve-form.component.html',
  styleUrls: ['./movie-reserve-form.component.css']
})
export class MovieReserveFormComponent implements OnInit {

  @Input() selectedSeats: string[];
  formFirstName: string = '';
  formLastName: string = '';
  formPhoneNumber: string = '';

  constructor() {
  }

  ngOnInit() {
    // this.selectedSeats = [];
  }

  onSubmit() {
    alert("Thanks for submitting! Data: " + JSON.stringify(new ReservationForm(this.selectedSeats, this.formFirstName,
      this.formLastName, this.formPhoneNumber)));
  }
}

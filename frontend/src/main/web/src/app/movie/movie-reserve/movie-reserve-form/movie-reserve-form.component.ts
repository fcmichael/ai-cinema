import {Component, Input, OnInit} from '@angular/core';
import {ReservationForm} from "./reservation-form";
import {ShowService} from "../../show.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-movie-reserve-form',
  templateUrl: './movie-reserve-form.component.html',
  styleUrls: ['./movie-reserve-form.component.css']
})
export class MovieReserveFormComponent implements OnInit {

  @Input() showId: number;
  @Input() selectedSeats: string[];
  formFirstName: string = '';
  formLastName: string = '';
  formPhoneNumber: string = '';

  constructor(private showService: ShowService, private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    let form = new ReservationForm(this.selectedSeats, this.formFirstName, this.formLastName, this.formPhoneNumber);
    this.showService.makeReservation(this.showId, form).subscribe(() => {
      this.router.navigate(['/repertuar/' + this.showId + '/rezerwacja/sukces']);
    });
  }
}

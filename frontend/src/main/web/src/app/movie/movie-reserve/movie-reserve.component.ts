import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Show} from "../show";
import {ShowService} from "../show.service";
import {switchMap} from "rxjs/operators";
import {Seat} from "./seat";

@Component({
  selector: 'app-movie-reserve',
  templateUrl: './movie-reserve.component.html',
  styleUrls: ['./movie-reserve.component.css']
})
export class MovieReserveComponent implements OnInit {

  alphabet: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'];

  show: Show;
  selectedSeats: string[];
  seats: Seat[][];

  constructor(private showService: ShowService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getShow();
  }

  private getShow() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.showService.getShow(parseInt(params.get('id')));
      })
    ).subscribe(show => {
      this.show = show;
      this.createSeatsArray();
    });
  }

  private createSeatsArray() {
    this.seats = [];

    for (let i = 0; i < this.show.auditoriumRows; i++) {
      this.seats[i] = [];
      for (let j = 0; j < this.show.auditoriumColumns; j++) {
        let name = this.alphabet[i] + (j + 1);
        this.seats[i][j] = new Seat(name, this.show.reservedSeats.includes(name), false);
      }
    }
  }

  private seatChange(i, j, event) {
    this.seats[i][j].checked = event.checked;
    const arr: Seat[] = [].concat(...this.seats);
    this.selectedSeats = arr.filter(value => value.checked).map(value => value.name);
  }
}

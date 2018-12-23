import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Show} from "../show";
import {ShowService} from "../show.service";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-movie-reserve',
  templateUrl: './movie-reserve.component.html',
  styleUrls: ['./movie-reserve.component.css']
})
export class MovieReserveComponent implements OnInit {

  alphabet: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'];

  show: Show;
  rowsCount: number;
  columnsCount: number;
  reservedSeats: string[];

  constructor(private showService: ShowService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getShow();
    this.getReservedSeats();
  }

  private getShow() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.showService.getShow(parseInt(params.get('id')));
      })
    ).subscribe(show => {
      this.show = show;
      this.rowsCount = show.auditoriumRows;
      this.columnsCount = show.auditoriumColumns;
    });
  }

  private getReservedSeats() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.showService.getReservedSeatsForShow(parseInt(params.get('id')));
      })
    ).subscribe(reservedSeats => this.reservedSeats = reservedSeats);
  }

  private array(n: number): number[] {
    return Array(n);
  }
}

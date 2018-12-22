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

  show: Show;
  rowsCount: number;
  columnsCount: number;

  seats: number[][] = [
    [1, 0, 1, 1, 1, 1, 0],
    [0, 1, 1, 1, 0, 1, 1],
    [0, 1, 0, 1, 0, 1, 0],
    [1, 0, 1, 1, 0, 1, 0],
    [1, 1, 1, 0, 0, 1, 1]
  ];

  constructor(private showService: ShowService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.showService.getShow(parseInt(params.get('id')));
      })
    ).subscribe(show => {
      this.show = show;
      this.rowsCount = show.auditoriumRows;
      this.columnsCount = show.auditoriumColumns;
    });
  }

  private array(n: number): number[] {
    return Array(n);
  }
}

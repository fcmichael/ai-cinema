import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Show} from "../../show";
import {ShowService} from "../../show.service";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-movie-reserve-success',
  templateUrl: './movie-reserve-success.component.html',
  styleUrls: ['./movie-reserve-success.component.css']
})
export class MovieReserveSuccessComponent implements OnInit {

  show: Show;

  constructor(private showService: ShowService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.showService.getShow(parseInt(params.get('id')));
      })
    ).subscribe(show => {
      this.show = show;
    });
  }

}

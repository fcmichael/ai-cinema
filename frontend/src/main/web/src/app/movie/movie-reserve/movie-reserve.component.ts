import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Show} from "../show";
import {ShowService} from "../show.service";
import {switchMap} from "rxjs/operators";
import {Seat} from "./seat";
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Component({
  selector: 'app-movie-reserve',
  templateUrl: './movie-reserve.component.html',
  styleUrls: ['./movie-reserve.component.css']
})
export class MovieReserveComponent implements OnInit {

  alphabet: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'];

  show: Show;
  selectedSeats: string[] = [];
  seats: Seat[][];
  reservedSeats: string[] = [];
  private socketUrl: string = 'http://localhost:8080/jsa-stomp-endpoint';
  private stompClient;

  constructor(private showService: ShowService, private route: ActivatedRoute) {
    this.initializeWebSocketConnection();
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
      this.reservedSeats = show.reservedSeats;
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
    this.seats[i][j].reserved = event.checked;
    const arr: Seat[] = [].concat(...this.seats);
    this.selectedSeats = arr.filter(value => value.checked).map(value => value.name);
    this.sendReservedSeat(this.seats[i][j]);
  }


  initializeWebSocketConnection() {
    const ws = new SockJS(this.socketUrl);
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;
    const _this = this;

    this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe('/topic/hi/single' + '/' + _this.show.id, function (hello) {
        const a: Seat = JSON.parse(hello.body);
        let row = a.name.codePointAt(0) - 65;
        let column = parseInt(a.name.substring(1));
        _this.seats[row][column - 1].reserved = a.reserved;
      });

      _this.stompClient.subscribe('/topic/hi/multi' + '/' + _this.show.id, function (hello) {
        const a: String[] = JSON.parse(hello.body);
        for (let i = 0; i < a.length; i++) {
          let row = a[i].codePointAt(0) - 65;
          let column = parseInt(a[i].substring(1));
          _this.seats[row][column - 1].reserved = false;
        }
      });
    });

  }

  sendReservedSeat(seat: Seat) {
    this.stompClient.send('/jsa/hello/single' + '/' + this.show.id, {}, JSON.stringify(seat));
  }

  releaseSeats() {
    this.stompClient.send('/jsa/hello/multi' + '/' + this.show.id, {}, JSON.stringify(this.selectedSeats));
  }
}

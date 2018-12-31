import {Component, OnInit} from '@angular/core';
import {TicketType} from "../ticket-type";
import {TicketService} from "../ticket.service";

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.css']
})
export class PriceListComponent implements OnInit {

  ticketTypes: TicketType[];
  displayedColumns: string[] = ['category', 'price'];

  constructor(private ticketService: TicketService) {
  }

  ngOnInit() {
    this.getTicketTypes();
  }

  getTicketTypes() {
    this.ticketService.getTicketTypes().subscribe(ticketTypes => this.ticketTypes = ticketTypes);
  }

}

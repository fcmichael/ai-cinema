import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TicketType} from "./ticket-type";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private ticketUrl: string = 'http://localhost:8080/tickets';

  constructor(private httpClient: HttpClient) { }

  getTicketTypes(): Observable<TicketType[]> {
    let url = this.ticketUrl + '/types';

    return this.httpClient.get<TicketType[]>(url);
  }
}

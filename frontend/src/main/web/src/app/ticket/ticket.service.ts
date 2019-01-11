import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TicketType} from "./ticket-type";
import {TICKETS_URL} from "../url-config";

@Injectable({
  providedIn: 'root'
})
export class TicketService {


  constructor(private httpClient: HttpClient) { }

  getTicketTypes(): Observable<TicketType[]> {
    let url = TICKETS_URL + '/types';

    return this.httpClient.get<TicketType[]>(url);
  }
}

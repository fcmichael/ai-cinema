import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DomSanitizer} from "@angular/platform-browser";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Event} from "./event";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventUrl: string = 'http://localhost:8080/events';

  constructor(private httpClient: HttpClient, private sanitizer: DomSanitizer) {
  }

  getEvent(id: number): Observable<Event> {
    return this.httpClient.get<Event>(this.eventUrl + '/' + id)
      .pipe(map(event => {
        return this.mapData(event);
      }));
  }

  getEvents(): Observable<Event[]> {
    return this.httpClient.get<Event[]>(this.eventUrl)
      .pipe(map((events: Event[]) => {
        return events.map(event => {
          return this.mapData(event);
        });
      }));
  }

  mapData(event: Event): Event {
    return new Event(
      event.id,
      event.title,
      event.eventDate,
      event.shortDescription,
      event.description.replace('\n', "<br>"),
      this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + event.image)
    )
  }
}

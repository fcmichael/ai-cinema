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

  getEvents(): Observable<Event[]> {
    return this.httpClient.get<Event[]>(this.eventUrl)
      .pipe(map((events: Event[]) => {
        return events.map(r => {
          return new Event(
            r.id,
            r.title,
            r.eventDate,
            r.shortDescription,
            r.description,
            this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + r.image)
          )
        });
      }));
  }
}

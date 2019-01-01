import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {EventService} from "../event.service";
import {Event} from "../event";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  event: Event;

  constructor(private eventService: EventService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getEvent();
  }

  private getEvent() {
    this.route.paramMap.pipe(switchMap(params => {
        return this.eventService.getEvent(parseInt(params.get('id')));
      })
    ).subscribe(event => this.event = event);
  }

}

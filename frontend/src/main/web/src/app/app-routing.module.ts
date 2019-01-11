import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PriceListComponent} from "./ticket/price-list/price-list.component";
import {MovieReserveComponent} from "./movie/movie-reserve/movie-reserve.component";
import {MovieReserveSuccessComponent} from "./movie/movie-reserve/movie-reserve-success/movie-reserve-success.component";
import {EventListComponent} from "./event/event-list/event-list.component";
import {EventDetailsComponent} from "./event/event-details/event-details.component";
import {LoginComponent} from "./security/login/login.component";
import {LoginGuard} from "./security/guard/login.guard";
import {AdminComponent} from "./admin/admin.component";
import {AdminGuard} from "./security/guard/admin.guard";
import {AdminMovieComponent} from "./admin/admin-movie/admin-movie.component";
import {AdminProgrammeComponent} from "./admin/admin-programme/admin-programme.component";
import {AdminEventComponent} from "./admin/admin-event/admin-event.component";
import {AdminPriceListComponent} from "./admin/admin-price-list/admin-price-list.component";
import {AdminUserComponent} from "./admin/admin-user/admin-user.component";
import {ProgrammeComponent} from "./programme/programme.component";
import {MovieEditComponent} from "./movie/movie-edit/movie-edit.component";
import {MovieAddComponent} from "./movie/movie-add/movie-add.component";
import {ReservationGuard} from "./security/guard/reservation.guard";
import {ReservationCancelGuard} from "./security/guard/reservation-cancel-guard.service";

const appRoutes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuard],
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'filmy',
      },
      {
        path: 'filmy',
        component: AdminMovieComponent
      },
      {
        path: 'filmy/edycja/:id',
        component: MovieEditComponent
      },
      {
        path: 'filmy/nowy',
        component: MovieAddComponent
      },
      {
        path: 'repertuar',
        component: AdminProgrammeComponent
      },
      {
        path: 'wydarzenia',
        component: AdminEventComponent
      },
      {
        path: 'cennik',
        component: AdminPriceListComponent
      },
      {
        path: 'uzytkownicy',
        component: AdminUserComponent
      }
    ]
  },
  {
    path: 'cennik',
    component: PriceListComponent
  },
  {
    path: 'repertuar',
    component: ProgrammeComponent
  },
  {
    path: 'repertuar/:id/rezerwacja',
    component: MovieReserveComponent,
    canActivate: [ReservationGuard],
    canDeactivate: [ReservationCancelGuard]
  },
  {
    path: 'repertuar/:id/rezerwacja/sukces',
    component: MovieReserveSuccessComponent
  },
  {
    path: 'wydarzenia',
    component: EventListComponent
  },
  {
    path: 'wydarzenia/:id',
    component: EventDetailsComponent
  },
  {
    path: 'zaloguj',
    component: LoginComponent,
    canActivate: [LoginGuard]
  },
  {
    path: '',
    redirectTo: '/repertuar',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}

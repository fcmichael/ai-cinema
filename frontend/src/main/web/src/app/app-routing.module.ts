import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {MovieListComponent} from "./movie/movie-list/movie-list.component";
import {PriceListComponent} from "./ticket/price-list/price-list.component";
import {MovieReserveComponent} from "./movie/movie-reserve/movie-reserve.component";
import {MovieReserveSuccessComponent} from "./movie/movie-reserve/movie-reserve-success/movie-reserve-success.component";
import {EventListComponent} from "./event/event-list/event-list.component";
import {EventDetailsComponent} from "./event/event-details/event-details.component";
import {LoginComponent} from "./security/login/login.component";

const appRoutes: Routes = [
  {path: 'cennik', component: PriceListComponent},
  {path: 'repertuar', component: MovieListComponent},
  {path: 'repertuar/:id/rezerwacja', component: MovieReserveComponent},
  {path: 'repertuar/:id/rezerwacja/sukces', component: MovieReserveSuccessComponent},
  {path: 'wydarzenia', component: EventListComponent},
  {path: 'wydarzenia/:id', component: EventDetailsComponent},
  {path: 'zaloguj', component: LoginComponent},
  {path: '', redirectTo: '/repertuar', pathMatch: 'full'}
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

import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {MovieListComponent} from "./movie/movie-list/movie-list.component";
import {PriceListComponent} from "./price-list/price-list.component";
import {MovieReserveComponent} from "./movie/movie-reserve/movie-reserve.component";

const appRoutes: Routes = [
  {path: 'repertuar', component: MovieListComponent},
  {path: 'repertuar/:id', component: MovieReserveComponent},
  {path: 'cennik', component: PriceListComponent},
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
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {MovieListComponent} from "./movie/movie-list/movie-list.component";

const appRoutes: Routes = [
  {path: 'repertuar', component: MovieListComponent},
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

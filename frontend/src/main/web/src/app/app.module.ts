import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCheckboxModule,
  MatIconModule,
  MatInputModule,
  MatSelectModule
} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {AppComponent} from './app.component';
import {MovieListComponent} from './movie/movie-list/movie-list.component';
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";
import {NavbarComponent} from './navbar/navbar.component';
import {PriceListComponent} from './price-list/price-list.component';
import {MovieReserveComponent} from './movie/movie-reserve/movie-reserve.component';
import {MovieReserveFormComponent} from './movie/movie-reserve/movie-reserve-form/movie-reserve-form.component';

@NgModule({
  declarations: [
    AppComponent,
    MovieListComponent,
    NavbarComponent,
    PriceListComponent,
    MovieReserveComponent,
    MovieReserveFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatButtonModule,
    MatButtonToggleModule,
    FormsModule,
    MatIconModule,
    MatCheckboxModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

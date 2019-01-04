import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCheckboxModule,
  MatIconModule,
  MatInputModule,
  MatSelectModule,
  MatTableModule
} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {AppComponent} from './app.component';
import {MovieListComponent} from './movie/movie-list/movie-list.component';
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";
import {NavbarComponent} from './navbar/navbar.component';
import {PriceListComponent} from './ticket/price-list/price-list.component';
import {MovieReserveComponent} from './movie/movie-reserve/movie-reserve.component';
import {MovieReserveFormComponent} from './movie/movie-reserve/movie-reserve-form/movie-reserve-form.component';
import {MovieReserveSuccessComponent} from './movie/movie-reserve/movie-reserve-success/movie-reserve-success.component';
import {EventListComponent} from './event/event-list/event-list.component';
import {EventDetailsComponent} from './event/event-details/event-details.component';
import {LoginComponent} from './security/login/login.component';
import {JwtInterceptor} from "./security/jwt-interceptor";
import {JwtModule} from "@auth0/angular-jwt";

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    MovieListComponent,
    NavbarComponent,
    PriceListComponent,
    MovieReserveComponent,
    MovieReserveFormComponent,
    MovieReserveSuccessComponent,
    EventListComponent,
    EventDetailsComponent,
    LoginComponent
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
    MatInputModule,
    MatTableModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        authScheme: 'JWT'
      }
    })
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

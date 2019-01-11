import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";
import {TOKEN_NAME} from "./authentication.constant";
import {LOGIN_URL} from "../url-config";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient,
              private jwtHelper: JwtHelperService,
              private router: Router) {
  }

  isLoggedIn(): boolean {
    let token = this.getToken();
    return !!token && !this.jwtHelper.isTokenExpired(token);
  }

  getUsername(): string {
    let auth = this.jwtHelper.decodeToken(this.getToken());
    return auth.sub;
  }

  login(username: string, password: string) {
    return this.httpClient.post(LOGIN_URL, {username, password}, {observe: 'response'})
      .pipe(map(resp => {
        let token = resp.headers.get("Authorization");

        if (token) {
          localStorage.setItem(TOKEN_NAME, token);
        }

        return null;
      }));
  }

  logout() {
    localStorage.removeItem(TOKEN_NAME);
    this.router.navigate(['/']);
  }

  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

}

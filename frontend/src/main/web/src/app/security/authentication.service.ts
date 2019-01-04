import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./user";
import {map} from "rxjs/operators";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";
import {TOKEN_NAME} from "./authentication.constant";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  user: User = new User('', '');
  loginUrl: string = 'http://localhost:8080/login';

  constructor(private httpClient: HttpClient,
              private jwtHelper: JwtHelperService,
              private router: Router) {
  }

  isLoggedIn(): boolean {
    return !!this.user.token && !this.jwtHelper.isTokenExpired(this.user.token);
  }

  getUsername(): string {
    return this.user.username;
  }

  login(username: string, password: string) {
    return this.httpClient.post(this.loginUrl, {username, password}, {observe: 'response'})
      .pipe(map(resp => {
        let token = resp.headers.get("Authorization");

        if (token) {
          let auth = this.jwtHelper.decodeToken(token);
          this.user = new User(auth.sub, token);
          localStorage.setItem(TOKEN_NAME, token);
        }

        return null;
      }));
  }

  logout() {
    this.user = new User('', '');
    localStorage.removeItem(TOKEN_NAME);
    this.router.navigate(['/']);
  }

  getCurrentUser(): User {
    return this.user;
  }
}

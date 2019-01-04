import {Component, OnInit} from '@angular/core';
import {Login} from "./login";
import {AuthenticationService} from "../authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: Login = new Login('', '');
  unsuccessfulLogin: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
  }

  login() {
    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(
        () => {
          this.router.navigate(['/']);
        },
        () => {
          this.unsuccessfulLogin = true;
        }
      );
  }

}

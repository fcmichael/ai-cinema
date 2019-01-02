import {Component, OnInit} from '@angular/core';
import {Login} from "./login";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: Login = new Login('', '');

  constructor() {
  }

  ngOnInit() {
  }

  onSubmit() {
    alert('email: ' + this.model.email + ', password: ' + this.model.password);
  }

}

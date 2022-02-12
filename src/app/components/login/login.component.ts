import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  token: string;
  username: string;
  password: string;

  constructor(private loginService: LoginService) {
      this.token='';
      this.username='';
      this.password='';
  }

  ngOnInit(): void {

  }

  login(username: string, password: string) {
    console.log(username,password);
    this.username='';
    this.password='';
    this.loginService.login(username, password);
  }

}

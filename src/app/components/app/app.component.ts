import {Component, OnDestroy} from '@angular/core';
import {LoginService} from "../../services/login.service";
import { Router} from "@angular/router";
import {DecodedJWT} from "../../model";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy{

  loggedUser:DecodedJWT;

  constructor(private loginService : LoginService,private router:Router) {
      //localStorage.setItem('token', 'djyhawgdawgd')
    this.loggedUser = loginService.getPrivileges()
  }

  logout(){
    this.loginService.logout()
    this.router.navigate(['/'])
  }

  ngOnDestroy(): void {
    this.loginService.logout()
  }

}

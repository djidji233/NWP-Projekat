import {Component, OnDestroy} from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy{



  constructor(private loginService : LoginService) {
      //localStorage.setItem('token', 'djyhawgdawgd')
  }

  ngOnDestroy(): void {
    this.loginService.logout()
  }

}

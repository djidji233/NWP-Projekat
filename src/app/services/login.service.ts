import {Injectable, OnDestroy} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {DecodedJWT, LoginResponse} from "../model";
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class LoginService implements OnDestroy {

  private readonly loginUrl = 'http://localhost:8080/auth/login';
  private decodedJWT: DecodedJWT;


  constructor(private http: HttpClient) {
      this.decodedJWT =  {
        can_create_machines: false,
        can_create_users: false,
        can_delete_users: false,
        can_read_users: false,
        can_restart_machines: false,
        can_search_machines: false,
        can_start_machines: false,
        can_stop_machines: false,
        can_update_users: false,
        exp: 9999999999,
        iat: 9999999999,
        sub: 'init'
      }
  }

  ngOnDestroy(): void {
    this.logout()
  }

  login(username:string, password:string) {
    return this.http.post<LoginResponse>(this.loginUrl, {
      username: username,
      password: password
     }).subscribe((response: { jwt: string; }) => {
      console.log(response.jwt)
      localStorage.setItem('JWT', response.jwt)
      this.decodedJWT  = jwt_decode(response.jwt)
      // console.log(this.decodedJWT?.can_create_users)
    })
  }

  public getPrivileges() : DecodedJWT {
    return <DecodedJWT>this.decodedJWT
  }


  logout() {
    localStorage.removeItem("jwt")
  }
}

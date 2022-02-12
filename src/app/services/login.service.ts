import {Injectable, OnDestroy} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {DecodedJWT, LoginResponse} from "../model";
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class LoginService implements OnDestroy {

  private readonly loginUrl = 'http://localhost:8080/auth/login';
  private decodedJWT: DecodedJWT | undefined;


  constructor(private http: HttpClient) {

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

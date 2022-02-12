import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  private readonly usersUrl = 'http://localhost:8080/api/users';
  private authorization = 'Bearer ' + localStorage.getItem('jwt');
  private machines: Observable<User[]>;

  constructor(private http: HttpClient) {
    this.machines = new Observable<[]>();
  }

}

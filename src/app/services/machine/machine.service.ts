import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Machine} from "../../model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  private readonly machinesUrl = 'http://localhost:8080/api/machines';
  private authorization = 'Bearer ' + localStorage.getItem('JWT');
  private machines: Observable<Machine[]>;

  constructor(private http: HttpClient) {
    this.machines = new Observable<[]>();
  }

  public getMachines(): Observable<Machine[]> {
    return this.machines;
  }

  public fetchMachines(): Observable<Machine[]> {
    this.machines = this.http.get<Machine[]>(this.machinesUrl, {
      params: {}, headers: {
        Authorization: this.authorization
      }
    });
    return this.machines;
  }

  public createMachine(machineName: string): Observable<Machine> {
    return this.http.post<Machine>(this.machinesUrl + '/create', {},{
      params: {
        machineName: machineName
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

}





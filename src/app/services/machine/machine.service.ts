import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Machine, MachineStatus} from "../../model";
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

  public startMachine(machineId:number) : Observable<MachineStatus> {
    return this.http.put<MachineStatus>(this.machinesUrl + '/start', {},{
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public stopMachine(machineId:number) : Observable<MachineStatus> {
    return this.http.put<MachineStatus>(this.machinesUrl + '/stop', {},{
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public restartMachine(machineId:number) : Observable<MachineStatus> {
    return this.http.put<MachineStatus>(this.machinesUrl + '/restart', {},{
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public destroyMachine(machineId:number) {
    return this.http.put(this.machinesUrl + '/destroy', {},{
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

}





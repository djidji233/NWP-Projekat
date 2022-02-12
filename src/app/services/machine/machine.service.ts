import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Machine, MachineStatus} from "../../model";
import {HttpClient, HttpParams} from "@angular/common/http";

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
    return this.http.post<Machine>(this.machinesUrl + '/create', {}, {
      params: {
        machineName: machineName
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public startMachine(machineId: number): Observable<MachineStatus> {
    return this.http.put<MachineStatus>(this.machinesUrl + '/start', {}, {
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public stopMachine(machineId: number): Observable<MachineStatus> {
    return this.http.put<MachineStatus>(this.machinesUrl + '/stop', {}, {
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public restartMachine(machineId: number): Observable<MachineStatus> {
    return this.http.put<MachineStatus>(this.machinesUrl + '/restart', {}, {
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public destroyMachine(machineId: number) {
    return this.http.put(this.machinesUrl + '/destroy', {}, {
      params: {
        machineId: machineId
      }, headers: {
        Authorization: this.authorization,
        //'Access-Control-Allow-Origin':'*'
      }
    })
  }

  public searchMachines(machineName: string, status: string, dateFrom: string, dateTo: string): Observable<Machine[]> {
    //console.log(machineName, status, dateFrom, dateTo)

    let params = new HttpParams()


    if(machineName!=''){
      console.log("machineName: ",machineName)
      params.append("machineName", machineName)
    }
    if(dateFrom!=''){
      console.log("dateFrom: ",dateFrom)
      params.append("dateFrom", dateFrom)
    }
    if(dateTo!=''){
      console.log("dateTo: ",dateTo)
      params.append("dateTo", dateTo)
    }
    if(status!=''){
      console.log("status: ",status)
     //let statusList[] = status.split(",")
      params.append("status", status)
    }

    this.machines = this.http.post<Machine[]>(this.machinesUrl + '/search', {}, {
    params: params,
      headers: {
        Authorization: this.authorization
        //'Access-Control-Allow-Origin':'*'
      }
    })

    return this.machines
  }

}





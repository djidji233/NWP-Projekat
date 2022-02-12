import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {MachineService} from "../../services/machine/machine.service";
import {DecodedJWT, Machine} from "../../model";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-machine-search',
  templateUrl: './machine-search.component.html',
  styleUrls: ['./machine-search.component.css']
})
export class MachineSearchComponent implements OnInit {

  machineName:string;
  status:string;
  dateFrom:string
  dateTo:string
  machines: Machine[]
  loggedUser:DecodedJWT;

  constructor(private machineService: MachineService, private loginService:LoginService,private activatedRoute: ActivatedRoute) {
    this.machineName=''
    this.status=''
    this.dateFrom=''
    this.dateTo=''
    this.machines=[]
    this.loggedUser = loginService.getPrivileges()
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch() {
    this.machineService.fetchMachines().subscribe(machines => {
      console.log(machines);
      this.machines = machines;
    });
  }

  search(machineName:string, status:string, dateFrom:string, dateTo:string){

  }

  startMachine(machineId: number) {
    this.machineService.startMachine(machineId).subscribe(async () => {
      await delay(12000);
      this.fetch()
    })
  }
  stopMachine(machineId:number){
    this.machineService.stopMachine(machineId).subscribe(async () => {
      await delay(12000);
      this.fetch()
    })
  }
  restartMachine(machineId:number){
    this.machineService.restartMachine(machineId).subscribe(async () => {
      await delay(6000);
      this.fetch()
      await delay(6000);
      this.fetch()
    })
  }
  destroyMachine(machineId:number){
    this.machineService.destroyMachine(machineId).subscribe(()=>{
      this.fetch()
    })
  }

}

async function delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}

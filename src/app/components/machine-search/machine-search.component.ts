import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {MachineService} from "../../services/machine/machine.service";
import {Machine} from "../../model";
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
  loggedUser:string;

  constructor(private machineService: MachineService, private loginService:LoginService,private activatedRoute: ActivatedRoute) {
    this.machineName=''
    this.status=''
    this.dateFrom=''
    this.dateTo=''
    this.machines=[]
    this.loggedUser = loginService.getPrivileges().sub
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

}

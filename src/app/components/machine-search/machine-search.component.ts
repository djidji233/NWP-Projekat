import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {MachineService} from "../../services/machine/machine.service";
import {Machine} from "../../model";

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

  constructor(private machineService: MachineService, private activatedRoute: ActivatedRoute) {
    this.machineName=''
    this.status=''
    this.dateFrom=''
    this.dateTo=''
    this.machines=[]
  }

  ngOnInit(): void {
  }

  search(machineName:string, status:string, dateFrom:string, dateTo:string){

  }

}

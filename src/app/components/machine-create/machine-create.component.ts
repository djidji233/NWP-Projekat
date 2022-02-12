import { Component, OnInit } from '@angular/core';
import {MachineService} from "../../services/machine/machine.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-machine-create',
  templateUrl: './machine-create.component.html',
  styleUrls: ['./machine-create.component.css']
})
export class MachineCreateComponent implements OnInit {

  machineName:string;

  constructor(private machineService: MachineService, private activatedRoute: ActivatedRoute) {
    this.machineName=''
  }

  ngOnInit(): void {
  }

  create(machineName:string){

  }

}

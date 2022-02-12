import { Component, OnInit } from '@angular/core';
import {MachineService} from "../../services/machine/machine.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DecodedJWT} from "../../model";
import {LoginService} from "../../services/login.service";


@Component({
  selector: 'app-machine-create',
  templateUrl: './machine-create.component.html',
  styleUrls: ['./machine-create.component.css']
})
export class MachineCreateComponent implements OnInit {

  machineName:string;
  loggedUser:DecodedJWT

  constructor(private machineService: MachineService,private router:Router,private loginService:LoginService, private activatedRoute: ActivatedRoute) {
    this.machineName=''
    this.loggedUser = loginService.getPrivileges()
  }

  ngOnInit(): void {
    if(!this.loggedUser.can_create_machines) {
      window.alert("you dont have the privilege!")
      this.router.navigate(['/']);
    }
  }

  create(machineName:string){
      this.machineService.createMachine(machineName).subscribe(machine => {
        console.log(machine)
        window.alert("succesfully created machine " + machine.name)
      });
  }

}

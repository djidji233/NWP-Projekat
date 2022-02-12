import { Component, OnInit } from '@angular/core';
import {ErrorMessage, User} from "../../model";
import {UserService} from "../../services/user/user.service";
import {MachineService} from "../../services/machine/machine.service";

@Component({
  selector: 'app-machine-errors',
  templateUrl: './machine-errors.component.html',
  styleUrls: ['./machine-errors.component.css']
})
export class MachineErrorsComponent implements OnInit {

  public errors: ErrorMessage[];
  // public permissions = [];

  // Pomocu parametra u konstruktoru injektujemo UserService instancu u UserListComponent
  constructor(private machineService: MachineService) {
    this.errors = [];
  }

  ngOnInit(): void {
    this.fetch();
  }

  fetch() {
    // this.machineService.fetchUsers().subscribe(users => {
    //   console.log(users);
    //   this.users = users;
    // });
  }

}

import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {

  public firstName: string;
  public lastName: string;
  public username: string;
  public password: string
  public can_create_users: boolean;
  public can_read_users: boolean
  public can_update_users: boolean
  public can_delete_users: boolean
  public can_search_machines: boolean
  public can_start_machines: boolean
  public can_stop_machines: boolean
  public can_restart_machines: boolean
  public can_create_machines: boolean
  public can_destroy_machines: boolean


  constructor(private userService: UserService) {
    this.firstName = '';
    this.lastName = '';
    this.username = '';
    this.password = '';
    this.can_create_users = false
    this.can_read_users = false
    this.can_update_users = false
    this.can_delete_users = false
    this.can_search_machines = false
    this.can_start_machines = false
    this.can_stop_machines = false
    this.can_restart_machines = false
    this.can_create_machines = false
    this.can_destroy_machines = false
  }

  ngOnInit(): void {
  }

  addUser(firstName: string,
          lastName: string,
          username: string,
          password: string,
          can_create_users: boolean,
          can_read_users: boolean,
          can_update_users: boolean,
          can_delete_users: boolean,
          can_search_machines: boolean,
          can_start_machines: boolean,
          can_stop_machines: boolean,
          can_restart_machines: boolean,
          can_create_machines: boolean,
          can_destroy_machines: boolean) {
    console.log(firstName,lastName,username,password,can_create_users,can_read_users,can_update_users,can_delete_users,can_search_machines,can_start_machines,can_stop_machines,can_restart_machines,can_create_machines,can_destroy_machines)

    this.userService.addUser(firstName,lastName,username,password,can_create_users,can_read_users,can_update_users,can_delete_users,can_search_machines,can_start_machines,can_stop_machines,can_restart_machines,can_create_machines,can_destroy_machines).subscribe(user=>{
      console.log(user)
    })
  }

}

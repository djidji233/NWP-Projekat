import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../model";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  public id: number
  public firstName: string;
  public lastName: string;
  public username: string;
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


  constructor(private userService: UserService, private activatedRoute: ActivatedRoute) {
    this.id = 0;
    this.firstName = '';
    this.lastName = '';
    this.username = '';
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
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = Number(params.get('id'));
      console.log(this.id);
    })
  }

  ngOnInit(): void {


    this.userService.fetchUsers().subscribe((users: User[]) => {
      console.log(users);
      let user = users.filter(user => user.userId === this.id)[0];
      this.firstName = user.firstName
      this.lastName = user.lastName
      this.username = user.username;
      this.can_create_users = user.can_create_users
      this.can_read_users = user.can_read_users
      this.can_update_users = user.can_update_users
      this.can_delete_users = user.can_delete_users
      this.can_search_machines = user.can_search_machines
      this.can_start_machines = user.can_start_machines
      this.can_stop_machines = user.can_stop_machines
      this.can_restart_machines = user.can_restart_machines
      this.can_create_machines = user.can_create_machines
      this.can_destroy_machines = user.can_destroy_machines
      //   for(let permission in user.permissions){
      //
      //   }
      //   this.firstName = user.firstName
      //   this.firstName = user.firstName
      //   this.firstName = user.firstName
    });


  }

  editUser(id: number,
           firstName: string,
           lastName: string,
           username: string,
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
    // //console.log(firstName,lastName,email,password,createp,readp,updatep,deletep)
    // let createpermission = new PermissionClass(PermissionType.CAN_CREATE_USERS, createp)
    // let readpermission = new PermissionClass(PermissionType.CAN_READ_USERS, readp)
    // let updatepermission = new PermissionClass(PermissionType.CAN_UPDATE_USERS, updatep)
    // let deletepermission = new PermissionClass(PermissionType.CAN_DELETE_USERS, deletep)
    // let permissions = []
    // permissions.push(createpermission, readpermission, updatepermission, deletepermission)
    // // let user = new UserClass(firstName, lastName, email, password)
    // // user.addPermission(createpermission)
    // // user.addPermission(readpermission)
    // // user.addPermission(updatepermission)
    // // user.addPermission(deletepermission)
    // this.userService.updateUser(id,firstName, lastName, email, password,permissions).subscribe(user=>{
    //   console.log(user)
    // })
  }

}

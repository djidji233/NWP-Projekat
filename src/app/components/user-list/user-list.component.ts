import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {UserService} from 'src/app/services/user/user.service';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DecodedJWT, User} from "../../model";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  public users: User[];
  loggedUser: DecodedJWT;

  // Pomocu parametra u konstruktoru injektujemo UserService instancu u UserListComponent
  constructor(private userService: UserService, private loginService: LoginService, private router:Router) {
    this.users = [];
    this.loggedUser = loginService.getPrivileges()
  }

  ngOnInit(): void {
    if(this.loggedUser.can_read_users){
      this.fetch();
    } else {
      window.alert("you dont have the privilege!")
      this.router.navigate(['/']);
    }

  }

  fetch() {
    this.userService.fetchUsers().subscribe(users => {
      console.log(users);
      this.users = users;
    });
  }

  deleteUser(userId: number) {
    this.userService.deleteUser(userId).subscribe(() => {
      this.ngOnInit()
    })

  }


}

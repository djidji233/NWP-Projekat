import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {first, Observable} from 'rxjs';
import {User} from "../../model";

// Injectable omogucava dependancy injection https://angular.io/guide/dependency-injection
// providedIn oznacava na kom nivou ce biti dostupna instanca ovog servisa
// 'root' znaci da je UserService singleton na nivou cele aplikacije
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly usersUrl = 'http://localhost:8080/api/users';
  private authorization = 'Bearer ' + localStorage.getItem('JWT');
  private users: Observable<User[]>;

  constructor(private http: HttpClient) {
      this.users = new Observable<[]>();
  }

  public getUsers(): Observable<User[]> {
    return this.users;
  }

  public fetchUsers(): Observable<User[]> {
    this.users = this.http.get<User[]>(this.usersUrl, {
      params: {}, headers: {
        Authorization: this.authorization
      }
    });
    return this.users;
  }

  public updateUser(id:number,firstName:string,lastName:string,username:string,password:string,
                    can_create_users:boolean,can_read_users:boolean,can_update_users:boolean,
                    can_delete_users:boolean,can_search_machines:boolean,can_start_machines:boolean,
                    can_stop_machines:boolean,can_restart_machines:boolean,can_create_machines:boolean,
                    can_destroy_machines:boolean): Observable<User> {
      let user: Observable<User> = this.http.put<User>(this.usersUrl, {
        'id':id,
        'firstName': firstName,
        'lastName': lastName,
        'username':username,
        'password':password,
        'can_create_users':can_create_users,
        'can_read_users':can_read_users,
        'can_update_users':can_update_users,
        'can_delete_users':can_delete_users,
        'can_search_machines':can_search_machines,
        'can_start_machines':can_start_machines,
        'can_stop_machines':can_stop_machines,
        'can_restart_machines':can_restart_machines,
        'can_create_machines':can_create_machines,
        'can_destroy_machines':can_destroy_machines
      }, {
        headers: {
          Authorization: this.authorization
        }
      });
      return user;
    }

  public addUser(firstName:string,lastName:string,username:string,password:string,
                 can_create_users:boolean,can_read_users:boolean,can_update_users:boolean,
                 can_delete_users:boolean,can_search_machines:boolean,can_start_machines:boolean,
                 can_stop_machines:boolean,can_restart_machines:boolean,can_create_machines:boolean,
                 can_destroy_machines:boolean): Observable<User> {
    let user: Observable<User> = this.http.post<User>(this.usersUrl, {
      'firstName': firstName,
      'lastName': lastName,
      'username':username,
      'password':password,
      'can_create_users':can_create_users,
      'can_read_users':can_read_users,
      'can_update_users':can_update_users,
      'can_delete_users':can_delete_users,
      'can_search_machines':can_search_machines,
      'can_start_machines':can_start_machines,
      'can_stop_machines':can_stop_machines,
      'can_restart_machines':can_restart_machines,
      'can_create_machines':can_create_machines,
      'can_destroy_machines':can_destroy_machines
    }, {
      headers: {
        Authorization: this.authorization
      }
    });
    return user;
  }

  public deleteUser(userId:number): Observable<any> {
    let res = this.http.delete(this.usersUrl + '/' + userId,{
      headers: {
        Authorization: this.authorization
      }
    })
    return res;
  }


}

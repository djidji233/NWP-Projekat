import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuard} from "./auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {UserListComponent} from "./components/user-list/user-list.component";
import {UserAddComponent} from "./components/user-add/user-add.component";
import {UserEditComponent} from "./components/user-edit/user-edit.component";
import {MachineSearchComponent} from "./components/machine-search/machine-search.component";
import {MachineCreateComponent} from "./components/machine-create/machine-create.component";
import {MachineErrorsComponent} from "./components/machine-errors/machine-errors.component";

const routes: Routes = [
  // http://localhost:4200/
  { path: '', component: LoginComponent},
  // http://localhost:4200/users
  { path: 'users', component: UserListComponent, canActivate: [AuthGuard]},
  // http://localhost:4200/users/add
  { path: 'users/add', component: UserAddComponent, canActivate: [AuthGuard]},
  // http://localhost:4200/users/edit/{id}
  { path: 'users/edit/:id', component: UserEditComponent, canActivate: [AuthGuard]},
  // http://localhost:4200/machines/search
  { path: 'machines/search', component: MachineSearchComponent, canActivate: [AuthGuard]},
  // http://localhost:4200/machines/create
  { path: 'machines/create', component: MachineCreateComponent, canActivate: [AuthGuard]},
  // http://localhost:4200/machines/errors
  { path: 'machines/errors', component: MachineErrorsComponent, canActivate: [AuthGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

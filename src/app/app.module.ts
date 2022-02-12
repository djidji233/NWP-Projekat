import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { LoginComponent } from './components/login/login.component';
import {AppComponent} from "./components/app/app.component";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { UserListComponent } from './components/user-list/user-list.component';
import { UserAddComponent } from './components/user-add/user-add.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { MachineSearchComponent } from './components/machine-search/machine-search.component';
import { MachineCreateComponent } from './components/machine-create/machine-create.component';
import { MachineErrorsComponent } from './components/machine-errors/machine-errors.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent,
    UserAddComponent,
    UserEditComponent,
    MachineSearchComponent,
    MachineCreateComponent,
    MachineErrorsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

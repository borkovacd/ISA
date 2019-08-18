import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FormsModule} from "@angular/forms";
import {WelcomePageComponent} from "./welcome-page/welcome-page.component";
import { SystemAdministratorComponent} from './users/system-administrator/system-administrator.component';


const routes: Routes = [
  {path: 'welcomepage', component: WelcomePageComponent},
  {path: '', component: SystemAdministratorComponent, pathMatch: 'full'},
  {path: 'administratorPage', component: SystemAdministratorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

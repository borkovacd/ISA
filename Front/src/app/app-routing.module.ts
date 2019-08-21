import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FormsModule} from "@angular/forms";
import {WelcomePageComponent} from "./welcome-page/welcome-page.component";
import { SystemAdministratorComponent} from './users/system-administrator/system-administrator.component';
import {HotelAdministratorComponent} from './users/hotel-administrator/hotel-administrator.component';
import {RegistracijaComponent} from "./registracija/registracija.component";
import {PrijavaComponent} from "./prijava/prijava.component";

const routes: Routes = [
  {path: 'welcomepage', component: WelcomePageComponent,
  children: [
    {path: 'prijava', component: PrijavaComponent},
    {path:'registracija', component: RegistracijaComponent}
  ]},
  {path: '', component: HotelAdministratorComponent, pathMatch: 'full'},
  {path: 'administratorPage', component: SystemAdministratorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

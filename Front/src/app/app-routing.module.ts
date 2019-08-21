import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FormsModule} from "@angular/forms";
import {WelcomePageComponent} from "./welcome-page/welcome-page.component";
import { SystemAdministratorComponent} from './users/system-administrator/system-administrator.component';
import {HotelAdministratorComponent} from './users/hotel-administrator/hotel-administrator.component';
import {RegistracijaComponent} from "./registracija/registracija.component";
import {PrijavaComponent} from "./prijava/prijava.component";
import {EditHotelComponent} from './hotels/edit-hotel/edit-hotel.component';
import {RoomsComponent} from './hotels/rooms/rooms.component';

const routes: Routes = [
  {path: '', component: WelcomePageComponent , pathMatch: 'full',
    children: [
      {path: 'prijava', component: PrijavaComponent},
      {path: 'registracija', component: RegistracijaComponent}
    ]},
  {path: 'hotelAdminPage', component: HotelAdministratorComponent},
  {path: 'hotelAdminPage/hotel/:idHotela', component: EditHotelComponent},
  {path: 'hotelAdminPage/rooms/:idHotela', component: RoomsComponent},
  {path: 'systemAdminPage', component: SystemAdministratorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

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
import {AddEditRoomComponent} from './hotels/add-edit-room/add-edit-room.component';
import {AdditionalServicesComponent} from './hotels/additional-services/additional-services.component';
import {RegisteredUserComponent} from "./users/registered-user/registered-user.component";
import {RentAdministratorComponent} from "./users/rent-administrator/rent-administrator.component";
import {PricelistsComponent} from './hotels/pricelists/pricelists.component';
import {AddEditPricelistComponent} from './hotels/add-edit-pricelist/add-edit-pricelist.component';
import {PricesComponent} from './hotels/prices/prices.component';
import {AddEditPriceComponent} from './hotels/add-edit-price/add-edit-price.component';
import {AddAdditionalServiceComponent} from './hotels/add-additional-service/add-additional-service.component';

import {EditRentComponent} from "./rentServisi/edit-rent/edit-rent.component";
import {VozilaComponent} from "./rentServisi/vozila/vozila.component";
import {FilijaleComponent} from "./rentServisi/filijale/filijale.component";

import {WelcomePageHoteliSobeComponent} from './welcome-page/welcome-page-hoteli/welcome-page-hoteli-sobe/welcome-page-hoteli-sobe.component';
import {AddEditVoziloComponent} from "./rentServisi/add-edit-vozilo/add-edit-vozilo.component";
import {AddEditFilijalaComponent} from "./rentServisi/add-edit-filijala/add-edit-filijala.component";


const routes: Routes = [
  {path: '', component: WelcomePageComponent , pathMatch: 'full'},

  {path: 'hotelAdminPage', component: HotelAdministratorComponent},
  {path: 'hotelAdminPage/editHotel/:idHotela', component: EditHotelComponent},
  {path: 'hotelAdminPage/rooms/:idHotela', component: RoomsComponent},
  {path: 'hotelAdminPage/pricelists/:idHotela', component: PricelistsComponent},
  {path: 'hotelAdminPage/prices/:idHotela/:idPriceList', component: PricesComponent},
  {path: 'hotelAdminPage/additionalServices/:idHotela', component: AdditionalServicesComponent},
  {path: 'hotelAdminPage/room/:idHotela/:mode/:idRoom', component: AddEditRoomComponent},
  {path: 'hotelAdminPage/pricelist/:idHotela/:mode/:idPriceList', component: AddEditPricelistComponent},
  {path: 'hotelAdminPage/price/:idHotela/:idPriceList/:mode/:idPrice', component: AddEditPriceComponent},
  {path: 'hotelAdminPage/additionalService/:idHotela', component: AddAdditionalServiceComponent},
  {path: 'systemAdminPage', component: SystemAdministratorComponent},

  {path: 'welcomepage', component: WelcomePageComponent},
  {path: 'prijava', component: PrijavaComponent},
  {path: 'registracija', component: RegistracijaComponent},
  {path: 'registrovaniKorisnik', component: RegisteredUserComponent},


  {path: 'rentAdminPage', component: RentAdministratorComponent},
  {path: 'rentAdminPage/editRent/:idRent', component: EditRentComponent},
  {path: 'rentAdminPage/vozila/:idRent', component: VozilaComponent},
  {path: 'rentAdminPage/filijale/:idRent', component: FilijaleComponent},
  {path: 'rentAdminPage/vozilo/:idRent/:mode/:idVozilo', component: AddEditVoziloComponent},
  {path: 'rentAdminPage/filijala/:idRent/:mode/:idFilijala', component: AddEditFilijalaComponent},

  {path: 'welcomepage/rooms/:idHotela', component: WelcomePageHoteliSobeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

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
import {WelcomePageHoteliDodatneUslugeComponent} from './welcome-page/welcome-page-hoteli/welcome-page-hoteli-dodatne-usluge/welcome-page-hoteli-dodatne-usluge.component';
import {WelcomePageHoteliCenovnikComponent} from './welcome-page/welcome-page-hoteli/welcome-page-hoteli-cenovnik/welcome-page-hoteli-cenovnik.component';
import { AddEditFilijalaComponent } from './rentServisi/add-edit-filijala/add-edit-filijala.component';
import { AddEditVoziloComponent } from './rentServisi/add-edit-vozilo/add-edit-vozilo.component';
import {RegisteredUserHoteliSobeComponent} from './users/registered-user/registered-user-hoteli/registered-user-hoteli-sobe/registered-user-hoteli-sobe.component';
import {RegisteredUserHoteliDodatneUslugeComponent} from './users/registered-user/registered-user-hoteli/registered-user-hoteli-dodatne-usluge/registered-user-hoteli-dodatne-usluge.component';
import {PricelistsRentComponent} from "./rentServisi/pricelists-rent/pricelists-rent.component";
import {PricesRentComponent} from "./rentServisi/prices-rent/prices-rent.component";
import {AddEditPricelistRentComponent} from "./rentServisi/add-edit-pricelist-rent/add-edit-pricelist-rent.component";
import {AddEditPriceRentComponent} from "./rentServisi/add-edit-price-rent/add-edit-price-rent.component";
import {WelcomePageServisiComponent} from "./welcome-page/welcome-page-servisi/welcome-page-servisi.component";
import {WelcomePageServisiFilijaleComponent} from "./welcome-page/welcome-page-servisi/welcome-page-servisi-filijale/welcome-page-servisi-filijale.component";
import {WelcomePageServisiCenovnikComponent} from "./welcome-page/welcome-page-servisi/welcome-page-servisi-cenovnik/welcome-page-servisi-cenovnik.component";
import {WelcomePageServisiVozilaComponent} from "./welcome-page/welcome-page-servisi/welcome-page-servisi-vozila/welcome-page-servisi-vozila.component";
import {RegisteredUserServisiVozilaComponent} from "./users/registered-user/registered-user-servisi/registered-user-servisi-vozila/registered-user-servisi-vozila.component";
import {AttendanceGraphsComponent} from "./hotels/attendance-graphs/attendance-graphs.component";
import {BrzaRezervacijaHoteliComponent} from './brza-rezervacija/brza-rezervacija-hoteli/brza-rezervacija-hoteli.component';
import {BrzaRezervacijaRentServisiComponent} from './brza-rezervacija/brza-rezervacija-rent-servisi/brza-rezervacija-rent-servisi.component';
import {BrzaRezervacijaHoteliIzborComponent} from './brza-rezervacija/brza-rezervacija-hoteli/brza-rezervacija-hoteli-izbor/brza-rezervacija-hoteli-izbor.component';
import {BrzaRezervacijaSobeIzborComponent} from './brza-rezervacija/brza-rezervacija-hoteli/brza-rezervacija-sobe-izbor/brza-rezervacija-sobe-izbor.component';
import {RevenuesComponent} from './hotels/revenues/revenues.component';
import {AttendanceGraphsRentComponent} from "./rentServisi/attendance-graphs-rent/attendance-graphs-rent.component";
import {RevenuesRentComponent} from "./rentServisi/revenues-rent/revenues-rent.component";
import {BrzaRezervacijaRentServisiIzborComponent} from "./brza-rezervacija/brza-rezervacija-rent-servisi/brza-rezervacija-rent-servisi-izbor/brza-rezervacija-rent-servisi-izbor.component";
import {BrzaRezervacijaVozilaIzborComponent} from "./brza-rezervacija/brza-rezervacija-rent-servisi/brza-rezervacija-vozila-izbor/brza-rezervacija-vozila-izbor.component";


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
  {path: 'hotelAdminPage/attendanceGraphs/:idHotela', component: AttendanceGraphsComponent},
  {path: 'hotelAdminPage/revenues/:idHotela', component: RevenuesComponent},

  {path: 'systemAdminPage', component: SystemAdministratorComponent},

  {path: 'prijava', component: PrijavaComponent},
  {path: 'registracija', component: RegistracijaComponent},
  {path: 'welcomepage', component: WelcomePageComponent},
  {path: 'welcomepage/rooms/:idHotela', component: WelcomePageHoteliSobeComponent},
  {path: 'welcomepage/additionalServices/:idHotela', component: WelcomePageHoteliDodatneUslugeComponent},
  {path: 'welcomepage/pricelist/:idHotela', component: WelcomePageHoteliCenovnikComponent},
  {path: 'welcomepage/vozila/:idRent', component: WelcomePageServisiVozilaComponent},
  {path: 'welcomepage/filijale/:idRent', component: WelcomePageServisiFilijaleComponent},
  {path: 'welcomepage/pricelistRent/:idRent', component: WelcomePageServisiCenovnikComponent},


  {path: 'registeredUserPage', component: RegisteredUserComponent},
  {path: 'registeredUserPage/rooms/:idHotela', component: RegisteredUserHoteliSobeComponent},
  {path: 'registeredUserPage/additionalServices/:idHotela/:idRezervacije', component: RegisteredUserHoteliDodatneUslugeComponent},
  {path: 'registeredUserPage/vozila/:idRent', component: RegisteredUserServisiVozilaComponent},

  {path: 'brzaRezervacija/hoteli/:idRezervacijeLeta', component: BrzaRezervacijaHoteliComponent},
  {path: 'brzaRezervacija/hoteli/:idRezervacijeLeta/izbor', component: BrzaRezervacijaHoteliIzborComponent},
  {path: 'brzaRezervacija/sobe/:idRezervacijeLeta/:idHotela', component: BrzaRezervacijaSobeIzborComponent},

  {path: 'brzaRezervacija/rentServisi/:idRezervacijeLeta', component: BrzaRezervacijaRentServisiComponent},
  {path: 'brzaRezervacija/servisi/:idRezervacijeLeta/izbor', component: BrzaRezervacijaRentServisiIzborComponent},
  {path: 'brzaRezervacija/vozila/:idRezervacijeLeta/:idRent', component: BrzaRezervacijaVozilaIzborComponent},


  {path: 'rentAdminPage', component: RentAdministratorComponent},
  {path: 'rentAdminPage/editRent/:idRent', component: EditRentComponent},
  {path: 'rentAdminPage/vozila/:idRent', component: VozilaComponent},
  {path: 'rentAdminPage/filijale/:idRent', component: FilijaleComponent},
  {path: 'rentAdminPage', component: RentAdministratorComponent},
  {path: 'rentAdminPage/vozilo/:idRent/:mode/:idVozilo', component: AddEditVoziloComponent},
  {path: 'rentAdminPage/filijala/:idRent/:mode/:idFilijala', component: AddEditFilijalaComponent},
  {path: 'rentAdminPage/pricelistsRent/:idRent', component: PricelistsRentComponent},
  {path: 'rentAdminPage/pricesRent/:idRent/:idPriceList', component: PricesRentComponent },
  {path: 'rentAdminPage/pricelistRent/:idRent/:mode/:idPriceList', component: AddEditPricelistRentComponent },
  {path: 'rentAdminPage/priceRent/:idRent/:idPriceList/:mode/:idPrice', component: AddEditPriceRentComponent},
  {path: 'rentAdminPage/attendanceGraphsRent/:idRent', component: AttendanceGraphsRentComponent},
  {path: 'rentAdminPage/revenuesRent/:idRent', component: RevenuesRentComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true}), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

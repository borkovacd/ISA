import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { AppComponent } from './app.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HotelReservationService } from "./service/hotelReservation.service";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {AuthInterceptor} from "./http-interceptor/AuthInterceptor";
import {
  IgxTabsModule,
  IgxNavbarModule,
  IgxIconModule,
  IgxButtonGroupModule,
  IgxAvatarModule,
  IgxCheckboxModule,
  IgxDatePickerModule
} from 'igniteui-angular';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AgmCoreModule } from '@agm/core';

import { SystemAdministratorComponent} from './users/system-administrator/system-administrator.component';
import { SystemAdministratorAdministratoriComponent} from './users/system-administrator/system-administrator-administratori/system-administrator-administratori.component';
import { SystemAdministratorHoteliComponent} from './users/system-administrator/system-administrator-hoteli/system-administrator-hoteli.component';
import { SystemAdministratorAviokompanijeComponent} from './users/system-administrator/system-administrator-aviokompanije/system-administrator-aviokompanije.component';
import { SystemAdministratorRentcarComponent} from './users/system-administrator/system-administrator-rentcar/system-administrator-rentcar.component';
import { HotelAdministratorComponent} from './users/hotel-administrator/hotel-administrator.component';
import { HotelAdministratorHoteliComponent} from './users/hotel-administrator/hotel-administrator-hoteli/hotel-administrator-hoteli.component';
import { HotelAdministratorProfilComponent} from './users/hotel-administrator/hotel-administrator-profil/hotel-administrator-profil.component';

import { UserService} from './service/user.service';
import { HotelService} from './service/hotel.service';
import { RentCarService} from './service/rentcar.service';
import { AviokompanijaService} from './service/aviokompanija.service';
import {MatCheckboxModule, MatDatepickerModule, MatGridListModule} from '@angular/material';
import {MatTableModule} from '@angular/material';
import {PrijavaComponent} from './prijava/prijava.component';
import {RegistracijaComponent} from './registracija/registracija.component';
import { EditHotelComponent } from './hotels/edit-hotel/edit-hotel.component';
import { RoomsComponent } from './hotels/rooms/rooms.component';
import { AddEditRoomComponent } from './hotels/add-edit-room/add-edit-room.component';
import { HotelAdministratorProfilPageComponent } from './users/hotel-administrator/hotel-administrator-profil/hotel-administrator-profil-page/hotel-administrator-profil-page.component';
import { HotelAdministratorProfilEditComponent } from './users/hotel-administrator/hotel-administrator-profil/hotel-administrator-profil-edit/hotel-administrator-profil-edit.component';
import {RoomService} from './service/room.service';
import { AdditionalServicesComponent } from './hotels/additional-services/additional-services.component';
import { RegisteredUserComponent } from './users/registered-user/registered-user.component';
import { RentAdministratorComponent } from './users/rent-administrator/rent-administrator.component';
import { RentAdministratorServisiComponent } from './users/rent-administrator/rent-administrator-servisi/rent-administrator-servisi.component';
import { RentAdministratorProfilComponent } from './users/rent-administrator/rent-administrator-profil/rent-administrator-profil.component';
import { RentAdministratorProfilEditComponent } from './users/rent-administrator/rent-administrator-profil/rent-administrator-profil-edit/rent-administrator-profil-edit.component';
import { RentAdministratorProfilPageComponent } from './users/rent-administrator/rent-administrator-profil/rent-administrator-profil-page/rent-administrator-profil-page.component';
import { PricelistsComponent } from './hotels/pricelists/pricelists.component';
import { AddEditPricelistComponent } from './hotels/add-edit-pricelist/add-edit-pricelist.component';
import {PricelistService} from './service/pricelist.service';
import { PricesComponent } from './hotels/prices/prices.component';
import { AddEditPriceComponent } from './hotels/add-edit-price/add-edit-price.component';
import {PriceService} from './service/price.service';
import { AddEditVoziloComponent } from './rentServisi/add-edit-vozilo/add-edit-vozilo.component';
import { EditRentComponent } from './rentServisi/edit-rent/edit-rent.component';
import { VozilaComponent } from './rentServisi/vozila/vozila.component';
import { FilijaleComponent } from './rentServisi/filijale/filijale.component';
import { AddAdditionalServiceComponent } from './hotels/add-additional-service/add-additional-service.component';
import {AdditionalServiceService} from './service/additionalService.service';
import { WelcomePageHoteliComponent } from './welcome-page/welcome-page-hoteli/welcome-page-hoteli.component';

import { AddEditFilijalaComponent } from './rentServisi/add-edit-filijala/add-edit-filijala.component';

import { WelcomePageHoteliSobeComponent } from './welcome-page/welcome-page-hoteli/welcome-page-hoteli-sobe/welcome-page-hoteli-sobe.component';
import { WelcomePageHoteliDodatneUslugeComponent } from './welcome-page/welcome-page-hoteli/welcome-page-hoteli-dodatne-usluge/welcome-page-hoteli-dodatne-usluge.component';
import { WelcomePageHoteliCenovnikComponent } from './welcome-page/welcome-page-hoteli/welcome-page-hoteli-cenovnik/welcome-page-hoteli-cenovnik.component';
import {VoziloService} from "./service/vozilo.service";
import {LokacijaService} from "./service/lokacija.service";
import { RegisteredUserHoteliComponent } from './users/registered-user/registered-user-hoteli/registered-user-hoteli.component';
import { RegisteredUserHoteliSobeComponent } from './users/registered-user/registered-user-hoteli/registered-user-hoteli-sobe/registered-user-hoteli-sobe.component';
import { RegisteredUserHoteliDodatneUslugeComponent } from './users/registered-user/registered-user-hoteli/registered-user-hoteli-dodatne-usluge/registered-user-hoteli-dodatne-usluge.component';
import { AddEditPriceRentComponent } from './rentServisi/add-edit-price-rent/add-edit-price-rent.component';
import { AddEditPricelistRentComponent } from './rentServisi/add-edit-pricelist-rent/add-edit-pricelist-rent.component';
import { PricelistsRentComponent } from './rentServisi/pricelists-rent/pricelists-rent.component';
import { PricesRentComponent } from './rentServisi/prices-rent/prices-rent.component';
import {PriceRentService} from "./service/priceRent.service";
import {PricelistRentService} from "./service/pricelistRent.service";
import { WelcomePageServisiComponent } from './welcome-page/welcome-page-servisi/welcome-page-servisi.component';
import { WelcomePageServisiCenovnikComponent } from './welcome-page/welcome-page-servisi/welcome-page-servisi-cenovnik/welcome-page-servisi-cenovnik.component';
import { WelcomePageServisiFilijaleComponent } from './welcome-page/welcome-page-servisi/welcome-page-servisi-filijale/welcome-page-servisi-filijale.component';
import { WelcomePageServisiVozilaComponent } from './welcome-page/welcome-page-servisi/welcome-page-servisi-vozila/welcome-page-servisi-vozila.component';
import { RegisteredUserMyVozilaComponent } from './users/registered-user/registered-user-my-vozila/registered-user-my-vozila.component';
import { RegisteredUserMyHoteliComponent } from './users/registered-user/registered-user-my-hoteli/registered-user-my-hoteli.component';
import { RegisteredUserServisiComponent } from './users/registered-user/registered-user-servisi/registered-user-servisi.component';
import { RegisteredUserServisiVozilaComponent } from './users/registered-user/registered-user-servisi/registered-user-servisi-vozila/registered-user-servisi-vozila.component';
import {VoziloReservationService} from "./service/voziloReservation.service";
import { AttendanceGraphsComponent } from './hotels/attendance-graphs/attendance-graphs.component';
import {AuthService} from "./service/auth.service";

import {BrzaRezervacijaHoteliComponent} from "./brza-rezervacija/brza-rezervacija-hoteli/brza-rezervacija-hoteli.component";
import {BrzaRezervacijaRentServisiComponent} from "./brza-rezervacija/brza-rezervacija-rent-servisi/brza-rezervacija-rent-servisi.component";
import {BrzaRezervacijaHoteliIzborComponent} from "./brza-rezervacija/brza-rezervacija-hoteli/brza-rezervacija-hoteli-izbor/brza-rezervacija-hoteli-izbor.component";
import { BrzaRezervacijaSobeIzborComponent } from './brza-rezervacija/brza-rezervacija-hoteli/brza-rezervacija-sobe-izbor/brza-rezervacija-sobe-izbor.component';
import { AttendanceGraphsRentComponent } from './rentServisi/attendance-graphs-rent/attendance-graphs-rent.component';
import { RevenuesComponent } from './hotels/revenues/revenues.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomePageComponent,
    SystemAdministratorComponent,
    SystemAdministratorAdministratoriComponent,
    SystemAdministratorHoteliComponent,
    SystemAdministratorRentcarComponent,
    SystemAdministratorAviokompanijeComponent,
    HotelAdministratorComponent,
    HotelAdministratorHoteliComponent,
    HotelAdministratorProfilComponent,
    PrijavaComponent,
    RegistracijaComponent,
    EditHotelComponent,
    RoomsComponent,
    AddEditRoomComponent,
    HotelAdministratorProfilPageComponent,
    HotelAdministratorProfilEditComponent,
    AdditionalServicesComponent,
    RegisteredUserComponent,
    RentAdministratorComponent,
    RentAdministratorServisiComponent,
    RentAdministratorProfilComponent,
    RentAdministratorProfilEditComponent,
    RentAdministratorProfilPageComponent,
    PricelistsComponent,
    AddEditPricelistComponent,
    PricesComponent,
    AddEditPriceComponent,
    AddEditVoziloComponent,
    EditRentComponent,
    VozilaComponent,
    FilijaleComponent,
    AddAdditionalServiceComponent,
    WelcomePageHoteliComponent,

    AddEditFilijalaComponent,

    WelcomePageHoteliSobeComponent,

    WelcomePageHoteliDodatneUslugeComponent,

    WelcomePageHoteliCenovnikComponent,

    RegisteredUserHoteliComponent,

    RegisteredUserHoteliSobeComponent,

    RegisteredUserHoteliDodatneUslugeComponent,

    AddEditPriceRentComponent,

    AddEditPricelistRentComponent,

    PricelistsRentComponent,

    PricesRentComponent,

    WelcomePageServisiComponent,

    WelcomePageServisiCenovnikComponent,

    WelcomePageServisiFilijaleComponent,

    WelcomePageServisiVozilaComponent,

    RegisteredUserMyVozilaComponent,

    RegisteredUserMyHoteliComponent,

    RegisteredUserServisiComponent,

    RegisteredUserServisiVozilaComponent,

    AttendanceGraphsComponent,
    BrzaRezervacijaRentServisiComponent,
    BrzaRezervacijaHoteliIzborComponent,
    BrzaRezervacijaHoteliComponent,
    BrzaRezervacijaSobeIzborComponent,
    AttendanceGraphsRentComponent,
    RevenuesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    NgbModule,
    CommonModule,
    MatTabsModule,
    BrowserAnimationsModule,
    FormsModule,
    IgxTabsModule,
    IgxNavbarModule,
    IgxIconModule,
    IgxButtonGroupModule,
    ReactiveFormsModule,
    HttpClientModule,
    IgxAvatarModule,
    MatGridListModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAXwTtS46pzFqyqxA9lzUMkpmyBsVtkGHs',
      libraries: ['places']
    }),
    MatTableModule,
    MatCheckboxModule,
    IgxCheckboxModule,
    IgxDatePickerModule,
    MatDatepickerModule
  ],
  providers: [
    HotelService,
    AviokompanijaService,
    RentCarService,
    UserService,
    RoomService,
    PricelistService,
    PriceService,
    AdditionalServiceService,
    VoziloService,
    LokacijaService,
    PriceRentService,
    PricelistRentService,
    HotelReservationService,
    VoziloReservationService,
    AuthService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  entryComponents: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}

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
import {IgxTabsModule, IgxNavbarModule, IgxIconModule, IgxButtonGroupModule, IgxAvatarModule} from 'igniteui-angular';
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
import {HttpClientModule} from '@angular/common/http';
import {MatCheckboxModule, MatGridListModule} from '@angular/material';
import {MatTableModule} from '@angular/material';



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
    MatCheckboxModule
  ],
  providers: [
    HotelService,
    AviokompanijaService,
    RentCarService,
    UserService
  ],
  entryComponents: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}

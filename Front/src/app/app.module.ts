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
import { IgxTabsModule, IgxNavbarModule, IgxIconModule, IgxButtonGroupModule} from 'igniteui-angular';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { SystemAdministratorComponent} from './users/system-administrator/system-administrator.component';
import { SystemAdministratorAdministratoriComponent} from './users/system-administrator/system-administrator-administratori/system-administrator-administratori.component';
import { SystemAdministratorHoteliComponent} from './users/system-administrator/system-administrator-hoteli/system-administrator-hoteli.component';

import { UserService} from './service/user.service';
import { HotelService} from './service/hotel.service';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    WelcomePageComponent,
    SystemAdministratorComponent,
    SystemAdministratorAdministratoriComponent,
    SystemAdministratorHoteliComponent
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
    HttpClientModule
  ],
  providers: [
    HotelService,
    UserService
  ],
  entryComponents: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}

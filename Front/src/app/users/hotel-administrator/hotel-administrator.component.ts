import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";



@Component({
  selector : 'hotel-administrator',
  templateUrl : './hotel-administrator.component.html',
  styleUrls   : ['./hotel-administrator.component.scss']
})
export class HotelAdministratorComponent implements  OnInit {

  administrator : any = null;

  constructor(private  userService: UserService,
              protected router: Router,
              private authService: AuthService) {
    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["rentAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate([""]);
        } else if(data.uloga == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["systemAdminPage"]);
        } else if(data.uloga == "OBICAN_KORISNIK"){
          this.router.navigate(["registeredUserPage"]);
        }

      },

      error => {
        this.router.navigate(["prijava"]);
      }
    )
  }

  public ngOnInit() {

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });
  }
  logout()
  {
    this.authService.logOutUser();
  }
}

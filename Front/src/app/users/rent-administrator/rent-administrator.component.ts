import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-rent-administrator',
  templateUrl: './rent-administrator.component.html',
  styleUrls: ['./rent-administrator.component.css']
})
export class RentAdministratorComponent implements OnInit {

  administrator : any = null;

  constructor(private userService : UserService, private router: Router, private authService: AuthService) {
    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "ADMINISTRATOR_HOTELA"){
          this.router.navigate(["hotelAdminPage"]);
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

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });
  }

  logout()
  {
    this.authService.logOutUser();
  }

}

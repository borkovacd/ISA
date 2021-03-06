import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-brza-rezervacija-hoteli',
  templateUrl: './brza-rezervacija-hoteli.component.html',
  styleUrls: ['./brza-rezervacija-hoteli.component.css']
})
export class BrzaRezervacijaHoteliComponent implements OnInit {

  constructor(private userService : UserService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) {
    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["rentAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate([""]);
        } else if(data.uloga == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["systemAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_HOTELA"){
          this.router.navigate(["hotelAdminPage"]);
        }

      },

      error => {
        this.router.navigate(["prijava"]);
      }
    )
  }

  ngOnInit() {
  }

  logout()
  {
    this.authService.logOutUser();
  }

  rezervisi() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/hoteli/' + idRezervacijeLeta + '/izbor');

  }

  preskoci() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/rentServisi/' + idRezervacijeLeta);
  }
}

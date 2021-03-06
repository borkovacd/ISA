import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';
import {FormBuilder} from '@angular/forms';
import {RentCarService} from "../../../service/rentcar.service";
import {OcenaRentService} from "../../../service/ocenaRent.service";

@Component({
  selector: 'app-brza-rezervacija-rent-servisi-izbor',
  templateUrl: './brza-rezervacija-rent-servisi-izbor.component.html',
  styleUrls: ['./brza-rezervacija-rent-servisi-izbor.component.css'],
  styles: [`
    .star {
      position: relative;
      display: inline-block;
      font-size: 3rem;
      color: darkgray;
    }
    .full {
      color: dodgerblue;
    }
    .half {
      position: absolute;
      display: inline-block;
      overflow: hidden;
      color: dodgerblue;
    }
  `]
})
export class BrzaRezervacijaRentServisiIzborComponent implements OnInit {

  rents = [];

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService,
              public fb: FormBuilder,
              private rentService: RentCarService,
              private ocenaService: OcenaRentService) {
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
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.rentService.getAllRentsByAddress(idRezervacijeLeta).subscribe(data => {
      this.rents = data;

      for (let rent of this.rents)
      {
        this.ocenaService.getProsecnaOcenaRent(rent.rentACarId).subscribe(data => {
          rent.ocena = data ;
        })
      }
    });
  }

  izaberiRent(id: any) {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/vozila/' + idRezervacijeLeta + '/' + id);

  }

  goBack(){
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/rentServisi/' + idRezervacijeLeta);
  }

  logout()
  {
    this.authService.logOutUser();
  }

}

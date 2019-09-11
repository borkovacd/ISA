import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LokacijaService} from "../../../service/lokacija.service";
import {RentCarService} from "../../../service/rentcar.service";

@Component({
  selector: 'app-welcome-page-servisi-filijale',
  templateUrl: './welcome-page-servisi-filijale.component.html',
  styleUrls: ['./welcome-page-servisi-filijale.component.css']
})
export class WelcomePageServisiFilijaleComponent implements OnInit {

  filijale = [];
  idFilijala: any  ;
  nazivRent : string ;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private lokService: LokacijaService,
              private rentService: RentCarService) { }

  ngOnInit() {
    const idRent = this.route.snapshot.params.idRent ;
    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv ;
    }) ;

    this.lokService.getFilijaleRentACar(idRent).subscribe(data => {
      this.filijale = data ;
    }) ;
  }

  goBack() {
    this.router.navigateByUrl('/welcomepage' );

  }

  ulogujSe() {
    this.router.navigateByUrl('/prijava');
  }

  registrujSe() {
    this.router.navigateByUrl('/registracija');
  }

}

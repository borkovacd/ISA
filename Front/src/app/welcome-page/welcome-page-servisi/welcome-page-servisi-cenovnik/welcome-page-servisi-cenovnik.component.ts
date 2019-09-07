import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RentCarService} from "../../../service/rentcar.service";
import {PricelistRentService} from "../../../service/pricelistRent.service";
import {PriceRentService} from "../../../service/priceRent.service";

@Component({
  selector: 'app-welcome-page-servisi-cenovnik',
  templateUrl: './welcome-page-servisi-cenovnik.component.html',
  styleUrls: ['./welcome-page-servisi-cenovnik.component.css']
})
export class WelcomePageServisiCenovnikComponent implements OnInit {

  prices = []
  nazivRent : string;
  d1: any;
  d2: any;
  activePricelist: any;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private rentService: RentCarService,
              private priceService: PriceRentService,
              private pricelistService: PricelistRentService) { }

  ngOnInit() {

    const idRent = this.route.snapshot.params.idRent;

    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv;
    })

    this.pricelistService.getActivePricelistRent(idRent).subscribe(data => {

      if(data != null) {
        this.activePricelist = data;
        this.d1 = data.pocetakVazenja;
        this.d2 = data.prestanakVazenja;

        this.priceService.getAllPricesRent(this.activePricelist.id).subscribe(prices => {
          this.prices = prices;
        });
      }
    });
  }

  goBack(){
    this.router.navigateByUrl('welcomepage' );
  }

  ulogujSe() {
    this.router.navigateByUrl('/prijava');
  }

  registrujSe() {
    this.router.navigateByUrl('/registracija');
  }

}

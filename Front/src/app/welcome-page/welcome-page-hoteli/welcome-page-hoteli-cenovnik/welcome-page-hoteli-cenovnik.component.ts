import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../../service/hotel.service';
import {PricelistService} from '../../../service/pricelist.service';
import {PriceService} from '../../../service/price.service';

@Component({
  selector: 'app-welcome-page-hoteli-cenovnik',
  templateUrl: './welcome-page-hoteli-cenovnik.component.html',
  styleUrls: ['./welcome-page-hoteli-cenovnik.component.css']
})
export class WelcomePageHoteliCenovnikComponent implements OnInit {

  prices = []
  nazivHotela : string;
  d1: any;
  d2: any;
  activePricelist: any;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private priceService: PriceService,
              private pricelistService: PricelistService) {}

  ngOnInit() {

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

    this.pricelistService.getActivePricelist(idHotela).subscribe(data => {

      if(data != null) {
        this.activePricelist = data;
        this.d1 = data.pocetakVazenja;
        this.d2 = data.prestanakVazenja;

        this.priceService.getAllPrices(this.activePricelist.id).subscribe(prices => {
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

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

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private priceService: PriceService) {}

  ngOnInit() {

    /*const idPriceList = this.route.snapshot.params.idPriceList;

    this.priceService.getAllPrices(idPriceList).subscribe(data => {
      this.prices = data;
    })*/

  }

  addPrice() {
    const idPriceList = this.route.snapshot.params.idPriceList;
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/price/' + idHotela + '/' + idPriceList  + '/add/');
  }

  goBack(){
    this.router.navigateByUrl('welcomepage' );
  }

  editRoom(id: any) {

  }

  deleteRoom(id: any) {

  }

  ulogujSe() {
    this.router.navigateByUrl('/prijava');
  }

  registrujSe() {
    this.router.navigateByUrl('/registracija');
  }

}

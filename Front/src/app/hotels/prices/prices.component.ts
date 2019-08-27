import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../service/hotel.service';
import {PricelistService} from '../../service/pricelist.service';
import {PriceService} from '../../service/price.service';

@Component({
  selector: 'app-prices',
  templateUrl: './prices.component.html',
  styleUrls: ['./prices.component.css']
})
export class PricesComponent implements OnInit {

  prices = []

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private priceService: PriceService) {}

  ngOnInit() {

    const idPriceList = this.route.snapshot.params.idPriceList;

    this.priceService.getAllPrices(idPriceList).subscribe(data => {
      this.prices = data;
    })

  }

  addPrice() {
    const idPriceList = this.route.snapshot.params.idPriceList;
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/price/' + idHotela + '/' + idPriceList  + '/add/');
  }

  goBack() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/pricelists/' + idHotela);

  }

  editRoom(id: any) {

  }

  deleteRoom(id: any) {

  }
}

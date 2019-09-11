import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../service/hotel.service';
import {PricelistService} from '../../service/pricelist.service';
import {PriceService} from '../../service/price.service';
import {UserService} from '../../service/user.service';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-prices',
  templateUrl: './prices.component.html',
  styleUrls: ['./prices.component.css']
})
export class PricesComponent implements OnInit {

  administrator : any = null;

  prices = []
  nazivHotela : string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private priceService: PriceService,
              private  userService: UserService,
              private authService: AuthService) {}

  ngOnInit() {

      this.userService.getCurrentUser().subscribe(data => {
        this.administrator = data;
      });

    const idPriceList = this.route.snapshot.params.idPriceList;

    this.priceService.getAllPrices(idPriceList).subscribe(data => {
      this.prices = data;
    })

    const idHotela = this.route.snapshot.params.idHotela;
    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
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

  logout()
  {
    this.authService.logOutUser();
  }
}

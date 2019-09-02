import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RentCarService} from "../../service/rentcar.service";
import {PricelistRentService} from "../../service/pricelistRent.service";
import {PriceRentService} from "../../service/priceRent.service";

@Component({
  selector: 'app-prices-rent',
  templateUrl: './prices-rent.component.html',
  styleUrls: ['./prices-rent.component.css']
})
export class PricesRentComponent implements OnInit {

  prices = []

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private rentService: RentCarService,
              private priceRentService: PriceRentService) { }

  ngOnInit() {
    const idPriceList = this.route.snapshot.params.idPriceList;

    this.priceRentService.getAllPricesRent(idPriceList).subscribe(data => {
      this.prices = data;
    })
  }

  addPrice() {
    const idPriceList = this.route.snapshot.params.idPriceList;
    const idRent = this.route.snapshot.params.idRent;
    this.router.navigateByUrl('rentAdminPage/priceRent/' + idRent + '/' + idPriceList  + '/add/');
  }

  goBack() {
    const idRent = this.route.snapshot.params.idRent;
    this.router.navigateByUrl('rentAdminPage/pricelistsRent/' + idRent);

  }

  editPrice(id: any) {

  }

  deletePrice(id: any) {
    const idRent = this.route.snapshot.params.idRent ;
    const idPriceList = this.route.snapshot.params.idPriceList;

    this.priceRentService.obrisiStavkuCenovnik(idRent, idPriceList, id).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage/pricelistsRent/' +  idRent);
    });


  }

}

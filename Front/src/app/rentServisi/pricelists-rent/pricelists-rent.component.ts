import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RentCarService} from "../../service/rentcar.service";
import {VoziloService} from "../../service/vozilo.service";
import {PricelistRentService} from "../../service/pricelistRent.service";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-pricelists-rent',
  templateUrl: './pricelists-rent.component.html',
  styleUrls: ['./pricelists-rent.component.css']
})
export class PricelistsRentComponent implements OnInit {

  pricelists = []
  nazivRent: string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private rentService: RentCarService,
              private pricelistRentService: PricelistRentService,
              private authService: AuthService) { }

  ngOnInit() {

    const idRent = this.route.snapshot.params.idRent;

    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv;
    })

    this.pricelistRentService.getAllPricelistsRent(idRent).subscribe(data => {
      this.pricelists = data;
    })
  }

  logout()
  {
    this.authService.logOutUser();
  }

  goBack(){
    this.router.navigateByUrl('rentAdminPage' );
  }

  addPricelistRent() {
    const idRent = this.route.snapshot.params.idRent;
    this.router.navigateByUrl('rentAdminPage/pricelistRent/' +  idRent  + '/add/');
  }

  deletePricelistRent(id: any) {
    const idRent = this.route.snapshot.params.idRent ;

    this.pricelistRentService.obrisiCenovnik(idRent, id).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage');
    });
  }

  showPricelistRent(idPriceList: any) {
    const idRent = this.route.snapshot.params.idRent;
    this.router.navigateByUrl('rentAdminPage/pricesRent/' + idRent + '/' + idPriceList);
  }



}

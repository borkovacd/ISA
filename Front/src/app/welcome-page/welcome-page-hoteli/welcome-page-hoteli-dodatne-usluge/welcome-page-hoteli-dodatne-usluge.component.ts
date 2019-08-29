import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../../service/hotel.service';
import {AdditionalServiceService} from '../../../service/additionalService.service';

@Component({
  selector: 'app-welcome-page-hoteli-dodatne-usluge',
  templateUrl: './welcome-page-hoteli-dodatne-usluge.component.html',
  styleUrls: ['./welcome-page-hoteli-dodatne-usluge.component.css']
})
export class WelcomePageHoteliDodatneUslugeComponent implements OnInit {

  additionalServices = [];
  nazivHotela: string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private additionalServiceService: AdditionalServiceService) {}

  ngOnInit() {

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

    this.additionalServiceService.getAllAdditionalServices(idHotela).subscribe(data => {
      this.additionalServices = data;
    })
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


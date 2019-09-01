import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {RentCarService} from "../../service/rentcar.service";
import {Observable} from 'rxjs';


@Component({
  selector: 'app-welcome-page-servisi',
  templateUrl: './welcome-page-servisi.component.html',
  styleUrls: ['./welcome-page-servisi.component.css']
})
export class WelcomePageServisiComponent implements OnInit {

  rents = []

  constructor(protected router: Router, private rentService: RentCarService) { }

  ngOnInit() {
    this.rentService.getAllRents().subscribe(data => {
      this.rents = data ;
    });
  }

  pregledVozila(idRent: any)
  {
    this.router.navigateByUrl('welcomepage/vozila/' + idRent);
  }

  pregledFilijala(idRent: any)
  {
    this.router.navigateByUrl('welcomepage/filijale/' + idRent);

  }

  pregledCenovnika(idRent: any)
  {
    this.router.navigateByUrl('welcomepage/pricelistsRent/' + idRent);
  }

}

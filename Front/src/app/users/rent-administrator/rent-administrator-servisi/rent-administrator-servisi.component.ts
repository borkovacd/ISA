import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import {RentCarService} from "../../../service/rentcar.service";
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-rent-administrator-servisi',
  templateUrl: './rent-administrator-servisi.component.html',
  styleUrls: ['./rent-administrator-servisi.component.css']
})
export class RentAdministratorServisiComponent implements OnInit {

  rents = []

  constructor(protected router: Router, private rentService: RentCarService) { }

  ngOnInit() {

    this.rentService.getRentsByAdministrator().subscribe(data => {
      this.rents = data ;
    });
  }

  izmeniRent(id:any)
  {
    this.router.navigateByUrl('rentAdminPage/editRent/' + id);
  }

  pregledVozila(idRent: any)
  {
    this.router.navigateByUrl('rentAdminPage/vozila/' + idRent);
  }

  pregledFilijala(idRent: any)
  {
    this.router.navigateByUrl('rentAdminPage/filijale/' + idRent);

  }

  pregledCenovnika(idRent: any)
  {

  }

}

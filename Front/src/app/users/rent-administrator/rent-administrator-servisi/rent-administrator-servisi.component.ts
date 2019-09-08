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
    this.rentService.checkIfReservedRent(id).subscribe(data => {
      if (data == false){
        this.router.navigateByUrl('rentAdminPage/editRent/' + id);
      } else {
        alert('Vozilo u tom smestaju je rezervisano, pa se ne moze menjati!');
      }
    })

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
    this.router.navigateByUrl('rentAdminPage/pricelistsRent/' + idRent);
  }

  pregledGrafikaRent(idRent: any) {
    this.router.navigateByUrl('rentAdminPage/attendanceGraphsRent/' + idRent);
  }

  pregledPrihoda(idRent: any) {
    this.router.navigateByUrl('rentAdminPage/revenuesRent/' + idRent);
  }

}

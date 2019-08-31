import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LokacijaService} from "../../service/lokacija.service";
import {RentCarService} from "../../service/rentcar.service";

@Component({
  selector: 'app-filijale',
  templateUrl: './filijale.component.html',
  styleUrls: ['./filijale.component.css']
})
export class FilijaleComponent implements OnInit {

  filijale = [];
  idFilijala: any  ;
  nazivRent : string ;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private lokService: LokacijaService,
              private rentService: RentCarService) { }

  ngOnInit() {
    const idRent = this.route.snapshot.params.idRent ;
    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv ;
    }) ;

    this.lokService.getFilijaleRentACar(idRent).subscribe(data => {
      this.filijale = data ;
    }) ;

  }


  addFilijala()
  {
    const idRent = this.route.snapshot.params.idRent ;
    this.router.navigateByUrl('rentAdminPage/filijala/' + idRent + '/add/' );
  }

  editFilijala(id: any) {
    const idRent = this.route.snapshot.params.idRent ;
    this.router.navigateByUrl('rentAdminPage/filijala/' + idRent + '/edit/' + id);
  }

  deleteFilijala(id: any) {
    const idRent = this.route.snapshot.params.idRent ;

    this.lokService.obrisiFilijalu(idRent, id).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage');
    });
  }

  goBack() {
    this.router.navigateByUrl('rentAdminPage' );

  }



}

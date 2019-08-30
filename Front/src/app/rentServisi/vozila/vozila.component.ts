import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from "../../service/vozilo.service";
import {RentCarService} from "../../service/rentcar.service";

@Component({
  selector: 'app-vozila',
  templateUrl: './vozila.component.html',
  styleUrls: ['./vozila.component.css']
})

export class VozilaComponent implements OnInit {

  vozila = [];
  idVozilo : any;
  nazivRent: string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private voziloService: VoziloService,
              private rentService: RentCarService) { }

  ngOnInit() {

    const idRent = this.route.snapshot.params.idRent ;

    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv ;
    })

    this.voziloService.getVozilaRentACar(idRent).subscribe(data => {
      this.vozila = data;
    })

  }

  /*

  addVozilo()
  {
    const idRent = this.route.snapshot.params.idRent ;
    this.router.navigateByUrl('rentAdminPage/vozilo/' + idRent + '/add')
  }

  editVozilo(id: any)
  {
    const idRent  = this.route.snapshot.params.idRent ;
    this.voziloService.
  }


   */

}

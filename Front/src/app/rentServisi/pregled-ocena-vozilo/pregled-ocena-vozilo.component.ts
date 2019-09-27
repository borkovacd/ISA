import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {VoziloService} from "../../service/vozilo.service";
import {RentCarService} from "../../service/rentcar.service";
import {AuthService} from "../../service/auth.service";
import {OcenaVoziloService} from "../../service/ocenaVozilo.service";

@Component({
  selector: 'app-pregled-ocena-vozilo',
  templateUrl: './pregled-ocena-vozilo.component.html',
  styleUrls: ['./pregled-ocena-vozilo.component.css'],
  styles: [`
    .star {
      position: relative;
      display: inline-block;
      font-size: 3rem;
      color: darkgray;
    }
    .full {
      color: dodgerblue;
    }
    .half {
      position: absolute;
      display: inline-block;
      overflow: hidden;
      color: dodgerblue;
    }
  `]
})
export class PregledOcenaVoziloComponent implements OnInit {

  nazivVozilo: string;
  oceneVozilo: [];

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private voziloService: VoziloService,
              private rentService: RentCarService,
              private ocenaService: OcenaVoziloService,
              private authService: AuthService) { }

  ngOnInit() {
    const idVozilo = this.route.snapshot.params.idVozilo ;
    const idRent = this.route.snapshot.params.idRent ;


    this.voziloService.vratiJednoVozilo(idRent, idVozilo).subscribe(data => {
      this.nazivVozilo = data.naziv ;
    });

    this.ocenaService.vratiListuOcenaVozila(idVozilo).subscribe(data => {
      this.oceneVozilo = data;
    });

  }

  logout()
  {
    this.authService.logOutUser();
  }

  goBack() {
    this.router.navigateByUrl('rentAdminPage' );
  }

}

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {VoziloService} from "../../service/vozilo.service";
import {RentCarService} from "../../service/rentcar.service";
import {OcenaRentService} from "../../service/ocenaRent.service";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-pregled-ocena-rent',
  templateUrl: './pregled-ocena-rent.component.html',
  styleUrls: ['./pregled-ocena-rent.component.css'],
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
export class PregledOcenaRentComponent implements OnInit {

  nazivRent: string;
  oceneRent: [];

  constructor(protected  router: Router,
  private route: ActivatedRoute,
  private voziloService: VoziloService,
  private rentService: RentCarService,
  private ocenaService: OcenaRentService,
  private authService: AuthService) { }

  ngOnInit() {

    const idRent = this.route.snapshot.params.idRent ;

    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv ;
    });

    this.ocenaService.vratiListuOcenaRent(idRent).subscribe(data => {
      this.oceneRent = data;
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

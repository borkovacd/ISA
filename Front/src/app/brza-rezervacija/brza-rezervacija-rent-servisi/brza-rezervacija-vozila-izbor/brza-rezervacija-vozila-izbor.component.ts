import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';
import {FormBuilder} from '@angular/forms';
import {VoziloService} from "../../../service/vozilo.service";
import {VoziloReservationService} from "../../../service/voziloReservation.service";
import {OcenaVoziloService} from "../../../service/ocenaVozilo.service";

@Component({
  selector: 'app-brza-rezervacija-vozila-izbor',
  templateUrl: './brza-rezervacija-vozila-izbor.component.html',
  styleUrls: ['./brza-rezervacija-vozila-izbor.component.css']
})
export class BrzaRezervacijaVozilaIzborComponent implements OnInit {

  vozila = [];

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService,
              public fb: FormBuilder,
              private voziloService: VoziloService,
              private reservationService: VoziloReservationService,
              private ocenaService: OcenaVoziloService) { }

  ngOnInit() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    const idRent = this.route.snapshot.params.idRent;
    this.voziloService.getVozilaAtDiscount(idRezervacijeLeta, idRent).subscribe(data => {
      this.vozila = data;

      for (let vozilo of this.vozila)
      {
        this.ocenaService.getProsecnaOcenaVozila(vozilo.voziloId).subscribe(data => {
          vozilo.ocena = data ;
        })
      }
    });
  }

  goBack() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/rentServisi/' + idRezervacijeLeta + '/izbor');
  }

  logout()
  {
    this.authService.logOutUser();
  }

  rezervisi(id: any) {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    const idRent = this.route.snapshot.params.idRent;
    this.reservationService.createFastResRent(idRezervacijeLeta, idRent, id).subscribe(data => {
      alert('Uspesna rezervacija vozila!');
      this.router.navigateByUrl('registeredUserPage');
    });
  }

  zavrsiRezervaciju() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('registeredUserPage');
  }

}

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from "../../../service/vozilo.service";

@Component({
  selector: 'app-registered-user-vozila-korisnik',
  templateUrl: './registered-user-vozila-korisnik.component.html',
  styleUrls: ['./registered-user-vozila-korisnik.component.css']
})
export class RegisteredUserVozilaKorisnikComponent implements OnInit {

  vozila = [];
  idVozilo: any;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private voziloService: VoziloService,) { }

  ngOnInit() {
    this.voziloService.vratiVozilaKorisnika().subscribe(data => {
      this.vozila = data ;
    });
  }

  goBack() {
    this.router.navigateByUrl('registeredUserPage' );

  }


}

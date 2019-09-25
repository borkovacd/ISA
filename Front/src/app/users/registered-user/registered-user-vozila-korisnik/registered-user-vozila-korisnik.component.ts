import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from "../../../service/vozilo.service";
import {OcenaVoziloService} from "../../../service/ocenaVozilo.service";

@Component({
  selector: 'app-registered-user-vozila-korisnik',
  templateUrl: './registered-user-vozila-korisnik.component.html',
  styleUrls: ['./registered-user-vozila-korisnik.component.css'],
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
export class RegisteredUserVozilaKorisnikComponent implements OnInit {

  vozila = [];
  idVozilo: any;

  rating : any ;


  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private voziloService: VoziloService,
              private ocenaService: OcenaVoziloService) { }

  ngOnInit() {
    this.voziloService.vratiVozilaKorisnika().subscribe(data => {
      this.vozila = data ;

      for (let vozilo of this.vozila)
      {
        this.ocenaService.getProsecnaOcenaVozila(vozilo.voziloId).subscribe(data => {
          vozilo.ocena = data ;
        })
      }
    });
  }

  goBack() {
    this.router.navigateByUrl('registeredUserPage' );

  }

  vratiProsecnuOcenu(id: any)
  {
    this.ocenaService.getProsecnaOcenaVozila(id).subscribe(data => {
      this.rating = data;
      if (data == 0 || data == undefined)
      {
        alert('Za ovo vozilo nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ocena ovog vozila je: ' + data);
      }
    })
  }


}

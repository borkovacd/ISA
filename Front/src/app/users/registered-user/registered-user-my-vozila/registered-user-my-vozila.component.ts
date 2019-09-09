import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from "../../../service/vozilo.service";
import {VoziloReservationService} from "../../../service/voziloReservation.service";

@Component({
  selector: 'app-registered-user-my-vozila',
  templateUrl: './registered-user-my-vozila.component.html',
  styleUrls: ['./registered-user-my-vozila.component.css']
})
export class RegisteredUserMyVozilaComponent implements OnInit {

  rezervacije = [];
  idVozilo: any;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private voziloService: VoziloService,
              private rezervacijaService: VoziloReservationService
              ) { }

  ngOnInit() {

    this.rezervacijaService.listaRentRezervacijaKorisnik().subscribe(data => {
      this.rezervacije = data ;
    });

  }

  cancelReservation(id: any)
  {
    this.rezervacijaService.otkaziRezervacijuVozila(id).subscribe(data => {
      if (data == true) {
        alert('Uspesno otkazana rezervacija!');
        //this.router.navigateByUrl('registeredUserPage' );
        window.location.reload();
      }
      else {
        alert('Nije moguce otkazati rezervaciju!');
      }
    })
  }

  goBack() {
    this.router.navigateByUrl('registeredUserPage' );

  }

  oceniVozilo(id: any, idVozilo: any){
    this.rezervacijaService.dozvoljenoOcenjivanje(idVozilo).subscribe(data => {
      if(data == true){
        this.router.navigateByUrl('registeredUserPage/' + idVozilo + '/rating');
      }else{
        alert('Nije Vam dozvoljeno ocenjivanje, jer nije prosla rezervacija!');

      }
    })


  }

}

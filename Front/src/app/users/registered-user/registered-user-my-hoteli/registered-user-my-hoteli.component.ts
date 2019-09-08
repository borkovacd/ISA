import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from '../../../service/vozilo.service';
import {VoziloReservationService} from '../../../service/voziloReservation.service';
import {HotelReservationService} from '../../../service/hotelReservation.service';

@Component({
  selector: 'app-registered-user-my-hoteli',
  templateUrl: './registered-user-my-hoteli.component.html',
  styleUrls: ['./registered-user-my-hoteli.component.css']
})
export class RegisteredUserMyHoteliComponent implements OnInit {

  rezervacije = [];

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private rezervacijaHotelaService: HotelReservationService,
  ) {
  }

  ngOnInit() {

    this.rezervacijaHotelaService.listaHotelRezervacijaKorisnik().subscribe(data => {
      this.rezervacije = data;
    });

  }

  cancelReservation(id: any) {
    this.rezervacijaHotelaService.otkaziRezervacijuHotela(id).subscribe(data => {
      if (data == true) {
        alert('Uspesno otkazana rezervacija!');
        window.location.reload();
      } else {
        alert('Nije moguce otkazati rezervaciju!');
      }
    });
  }

  goBack() {
    this.router.navigateByUrl('registeredUserPage');

  }
}

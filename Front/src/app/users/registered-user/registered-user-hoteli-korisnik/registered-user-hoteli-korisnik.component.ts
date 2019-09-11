import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from '../../../service/vozilo.service';
import {OcenaVoziloService} from '../../../service/ocenaVozilo.service';
import {HotelService} from '../../../service/hotel.service';
import {OcenaHotelService} from '../../../service/ocenaHotel.service';

@Component({
  selector: 'app-registered-user-hoteli-korisnik',
  templateUrl: './registered-user-hoteli-korisnik.component.html',
  styleUrls: ['./registered-user-hoteli-korisnik.component.css']
})
export class RegisteredUserHoteliKorisnikComponent implements OnInit {

  hotels = [];
  idHotela: any;

  rating : any ;


  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private ocenaService: OcenaHotelService) { }

  ngOnInit() {
    this.hotelService.vratiHoteleKorisnika().subscribe(data => {
      this.hotels = data ;
    });
  }

  goBack() {
    this.router.navigateByUrl('registeredUserPage' );

  }

  vratiProsecnuOcenu(id: any)
  {
    this.ocenaService.getProsecnaOcenaHotel(id).subscribe(data => {
      this.rating = data;
      if (data == 0 || data == undefined)
      {
        alert('Za ovaj hotel nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ocena ovog hotela je: ' + data);
      }
    })
  }

}

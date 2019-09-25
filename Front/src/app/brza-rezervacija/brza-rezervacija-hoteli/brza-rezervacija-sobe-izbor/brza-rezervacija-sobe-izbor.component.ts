import {Component, NgZone, OnInit} from '@angular/core';
import {UserService} from '../../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';
import {FormBuilder} from '@angular/forms';
import {MapsAPILoader} from '@agm/core';
import {HotelService} from '../../../service/hotel.service';
import {RoomService} from '../../../service/room.service';
import {HotelReservationService} from '../../../service/hotelReservation.service';
import {OcenaSobaService} from "../../../service/ocenaSoba.service";

@Component({
  selector: 'app-brza-rezervacija-sobe-izbor',
  templateUrl: './brza-rezervacija-sobe-izbor.component.html',
  styleUrls: ['./brza-rezervacija-sobe-izbor.component.css'],
  styles: [`
    .star {
      position: relative;
      display: inline-block;
      font-size: 3rem;
      color: #d3d3d3;
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
export class BrzaRezervacijaSobeIzborComponent implements OnInit {

  rooms = [];

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService,
              public fb: FormBuilder,
              private roomService: RoomService,
              private hotelReservationService: HotelReservationService,
              private ocenaService: OcenaSobaService) { }

  ngOnInit() {

    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    const idHotela = this.route.snapshot.params.idHotela;
    this.roomService.getRoomsAtDiscount(idRezervacijeLeta, idHotela).subscribe(data => {
      this.rooms = data;

      for (let room of this.rooms)
      {
        this.ocenaService.getProsecnaOcenaSoba(room.id).subscribe(data => {
          room.ocena = data ;
        })
      }
    });
  }

  goBack() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/hoteli/' + idRezervacijeLeta + '/izbor');
  }

  logout()
  {
    this.authService.logOutUser();
  }

  rezervisi(id: any) {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    const idHotela = this.route.snapshot.params.idHotela;
    this.hotelReservationService.createOrChangeFastHotelReservation(idRezervacijeLeta, idHotela, id).subscribe(data => {
      alert('Uspesna rezervacija sobe!');
      location.reload();
    });
  }

  zavrsiRezervaciju() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/rentServisi/' + idRezervacijeLeta);
  }
}

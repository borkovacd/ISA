import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../../../service/room.service';
import {HotelService} from '../../../../service/hotel.service';
import {TimePeriodModel} from '../../../../model/timePeriod.model';
import {UserService} from '../../../../service/user.service';
import {CheckAvailabilityModel} from '../../../../model/checkAvailability.model';
import {SearchHotelsModel} from "../../../../model/searchHotels.model";
import {RoomReservationModel} from "../../../../model/roomReservation.model";
import {HotelReservationService} from "../../../../service/hotelReservation.service";
import {AuthService} from "../../../../service/auth.service";
import {OcenaHotelService} from "../../../../service/ocenaHotel.service";
import {OcenaSobaService} from "../../../../service/ocenaSoba.service";

@Component({
  selector: 'app-registered-user-hoteli-sobe',
  templateUrl: './registered-user-hoteli-sobe.component.html',
  styleUrls: ['./registered-user-hoteli-sobe.component.css'],
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
export class RegisteredUserHoteliSobeComponent implements OnInit {

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  public numberOfGuests: AbstractControl;
  public numberOfRooms: AbstractControl;
  public priceRange: AbstractControl;

  d1: any;
  d2: any;
  rooms = [];
  nazivHotela: string;
  timePeriodConfirmed: boolean;

  public check = false;
  listSelectedRooms = [];
  listSelectedRoomsID = [];
  values = '';
  dobarBrojSoba: boolean = false;

  rating : any ;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private hotelService: HotelService,
              private userService: UserService,
              private hotelReservationService: HotelReservationService,
              private authService: AuthService,
              private ocenaService: OcenaSobaService) {

    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["rentAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate([""]);
        } else if(data.uloga == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["systemAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_HOTELA"){
          this.router.navigate(["hotelAdminPage"]);
        }

      },

      error => {
        this.router.navigate(["prijava"]);
      }
    )

    this.form = this.fb.group({
      'startDate': ['', Validators.compose([Validators.required])],
      'endDate': ['', Validators.compose([Validators.required])],
      'numberOfGuests': ['', Validators.compose([Validators.required, Validators.pattern('[1-9]{1,2}$')])],
      'numberOfRooms': ['', Validators.compose([Validators.required, Validators.pattern('[1-9]{1,2}$')])],
      'priceRange': [''],
    })
    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
    this.numberOfGuests = this.form.controls['numberOfGuests'];
    this.numberOfRooms = this.form.controls['numberOfRooms'];
    this.priceRange = this.form.controls['priceRange'];

  }

  ngOnInit() {

    this.timePeriodConfirmed = false;

    this.dobarBrojSoba = false;


    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

  }

  goBack(){
    this.router.navigateByUrl('registeredUserPage' );
  }

  confirmClick() {

    this.timePeriodConfirmed = true;
    const idHotela = this.route.snapshot.params.idHotela;

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const checkAvailabilityModel = new CheckAvailabilityModel (
      this.d1,
      this.d2,
      this.numberOfGuests.value,
      this.numberOfRooms.value,
      this.priceRange.value,
    );

    this.roomService.checkAvailability(checkAvailabilityModel, idHotela).subscribe(data => {
      this.rooms = data;

      if (this.rooms!= null) {
        for (let room of this.rooms) {
          this.ocenaService.getProsecnaOcenaSoba(room.id).subscribe(data => {
            room.ocena = data;
          })
        }
      }
    });

  }

  logout()
  {
    this.authService.logOutUser();
  }

  addRoom(tipSobe: any, id: any) {
    this.check = false;

    if (this.listSelectedRooms.length === 0) {
      this.listSelectedRooms.push(tipSobe + id);
      this.listSelectedRoomsID.push(id);
      this.values += tipSobe + '(' + id + ')' + '    ';
    } else {
      for (var i = 0; i < this.listSelectedRooms.length; i++) {
        if (this.listSelectedRooms[i] == tipSobe + id) {
          this.check = true;
          break;
        }
      }
      if (this.check == false) {
        this.listSelectedRooms.push(tipSobe + id);
        this.listSelectedRoomsID.push(id);
        this.values += tipSobe + '(' + id + ')' + '    ';
      }
    }
    this.checkBrojSoba();
  }



  removeRoom(tipSobe: any, id: any) {
    this.check = false;

    for (var i = 0; i < this.listSelectedRooms.length; i++) {

      if (this.listSelectedRooms[i] == tipSobe+id) {
        this.listSelectedRooms = this.listSelectedRooms.filter(item => item != tipSobe+id);
        this.listSelectedRoomsID = this.listSelectedRoomsID.filter(item => item != id);
        break;
      }
    }
    this.values = '';
    for (var i = 0; i < this.listSelectedRooms.length; i++) {
      this.values += this.listSelectedRooms[i] + '    ';
    }
    this.checkBrojSoba();
  }

  checkBrojSoba() {
    if(this.listSelectedRooms.length == this.numberOfRooms.value) {
      this.dobarBrojSoba = true;
    } else {
      this.dobarBrojSoba = false;
    }
  }

  nastaviRezervaciju() {
    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const roomReservation = new RoomReservationModel(
      this.d1,
      this.d2,
      this.numberOfGuests.value,
      this.listSelectedRoomsID
    );

    const idHotela = this.route.snapshot.params.idHotela;
    this.hotelReservationService.createHotelReservation(roomReservation).subscribe(data => {
      const idRezervacije = data.id;
      this.router.navigateByUrl('registeredUserPage/additionalServices/' + idHotela + '/' + idRezervacije);
    });
  }

  vratiProsecnuOcenu(id: any) {
    this.ocenaService.getProsecnaOcenaSoba(id).subscribe(data => {
      this.rating = data;
      if (data == 0 || data == undefined)
      {
        alert('Za ovu sobu nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ove sobe je: ' + data);
      }
    })
  }
}

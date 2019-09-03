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

@Component({
  selector: 'app-registered-user-hoteli-sobe',
  templateUrl: './registered-user-hoteli-sobe.component.html',
  styleUrls: ['./registered-user-hoteli-sobe.component.css']
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
  rooms = []
  nazivHotela: string;
  timePeriodConfirmed: boolean;

  public check = false;
  listSelectedRooms = [];
  listSelectedRoomsID = [];
  values = '';
  dobarBrojSoba: boolean = false;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private hotelService: HotelService,
              private userService: UserService,
              private hotelReservationService: HotelReservationService) {
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
    });

  }

  logout()
  {
    this.userService.logOut().subscribe(
      data => {
        this.router.navigate(['/welcomepage']);
      }
    )
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
      this.listSelectedRoomsID
    );

    const idHotela = this.route.snapshot.params.idHotela;
    this.hotelReservationService.createHotelReservation(roomReservation).subscribe(data => {
      this.router.navigateByUrl('registeredUserPage/additionalServices/' + idHotela);
    });
  }
}

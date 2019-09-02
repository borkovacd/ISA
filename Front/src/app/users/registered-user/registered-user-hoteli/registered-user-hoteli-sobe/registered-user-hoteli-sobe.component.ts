import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../../../service/room.service';
import {HotelService} from '../../../../service/hotel.service';
import {TimePeriodModel} from '../../../../model/timePeriod.model';
import {UserService} from '../../../../service/user.service';
import {CheckAvailabilityModel} from '../../../../model/checkAvailability.model';

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

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private hotelService: HotelService,
              private userService: UserService,) {
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

}

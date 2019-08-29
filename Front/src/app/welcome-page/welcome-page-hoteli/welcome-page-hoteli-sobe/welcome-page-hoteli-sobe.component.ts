import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../../service/room.service';
import {HotelService} from '../../../service/hotel.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TimePeriodModel} from '../../../model/timePeriod.model';

@Component({
  selector: 'app-welcome-page-hoteli-sobe',
  templateUrl: './welcome-page-hoteli-sobe.component.html',
  styleUrls: ['./welcome-page-hoteli-sobe.component.css']
})
export class WelcomePageHoteliSobeComponent implements OnInit {

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  d1: any;
  d2: any;
  rooms = []
  nazivHotela: string;
  timePeriodConfirmed: boolean;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private hotelService: HotelService) {
    this.form = this.fb.group({
      'startDate': ['', Validators.compose([Validators.required])],
      'endDate': ['', Validators.compose([Validators.required])],
    })
    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
  }

  ngOnInit() {

    this.timePeriodConfirmed = false;

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

  }

  goBack(){
    this.router.navigateByUrl('welcomepage' );
  }

  confirmClick() {

    this.timePeriodConfirmed = true;
    const idHotela = this.route.snapshot.params.idHotela;

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const timePeriod = new TimePeriodModel (
      this.d1,
      this.d2
    );

    this.roomService.getAvailableRooms(timePeriod, idHotela).subscribe(data => {
      this.rooms = data;
    });
  }

  ulogujSe() {
    this.router.navigateByUrl('/prijava');
  }

  registrujSe() {
    this.router.navigateByUrl('/registracija');
  }

}


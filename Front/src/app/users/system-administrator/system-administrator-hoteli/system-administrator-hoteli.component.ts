import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelModel} from '../../../model/hotel.model';
import {HotelService} from '../../../service/hotel.service';
import {UserService} from '../../../service/user.service';

@Component({
  selector : 'system-administrator-hoteli',
  templateUrl : './system-administrator-hoteli.component.html',
  styleUrls   : ['./system-administrator-hoteli.component.scss']
})
export class SystemAdministratorHoteliComponent implements  OnInit {

  public form: FormGroup;
  public name: AbstractControl;
  public address: AbstractControl;
  public description: AbstractControl;
  public administratorHotela: AbstractControl;

  administratoriHotela = []

  constructor(protected  router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              public hotelService: HotelService,
              public userService: UserService) {
    this.form = this.fb.group({
      'name': ['', Validators.compose([Validators.required])],
      'address': ['', Validators.compose([Validators.required])],
      'description': ['', Validators.compose([Validators.required])],
      'administratorHotela': ['', Validators.compose([Validators.required])]
    })

    this.name = this.form.controls['name'];
    this.address = this.form.controls['address'];
    this.description = this.form.controls['description'];
    this.administratorHotela = this.form.controls['administratorHotela'];

  }

  public ngOnInit() {

    this.userService.getHotelAdministrators().subscribe(data => {
      this.administratoriHotela = data;
    })
  }

  confirmClick() {
    this.registerHotel();
  }

  registerHotel() {
    const hotel = new HotelModel(
      this.name.value,
      this.address.value,
      this.description.value,
      this.administratorHotela.value,
    );

    this.hotelService.registerHotel(hotel).subscribe(data => {
      this.router.navigateByUrl('/welcomepage');
    })
  }

  exit() {
    this.router.navigateByUrl('/welcomepage');
  }
}

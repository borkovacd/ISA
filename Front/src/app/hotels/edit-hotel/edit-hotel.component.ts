import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../service/hotel.service';
import {UserService} from '../../service/user.service';
import {HotelModel} from '../../model/hotel.model';

@Component({
  selector: 'app-edit-hotel',
  templateUrl: './edit-hotel.component.html',
  styleUrls: ['./edit-hotel.component.css']
})
export class EditHotelComponent implements OnInit {

  public form: FormGroup;
  public name: AbstractControl;
  public address: AbstractControl;
  public description: AbstractControl;
  public administratorHotela: any;


  constructor(protected  router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              public hotelService: HotelService,
              public userService: UserService) {
    this.form = this.fb.group({
      'name': ['', Validators.compose([Validators.required])],
      'address': ['', Validators.compose([Validators.required])],
      'description': [''],
    })

    this.name = this.form.controls['name'];
    this.address = this.form.controls['address'];
    this.description = this.form.controls['description'];
  }

  ngOnInit() {
    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.form.controls['name'].setValue(data.naziv);
      this.form.controls['address'].setValue(data.adresa);
      this.form.controls['description'].setValue(data.opis);
      this.administratorHotela = data.administratorHotela;
    })
  }

  exit() {
    this.router.navigateByUrl('/hotelAdminPage');
  }

  confirmClick() {
    this.editHotel();
  }

  editHotel() {
    const hotel = new HotelModel(
      this.name.value,
      this.address.value,
      this.description.value,
      ''
    );

    const idHotela = this.route.snapshot.params.idHotela;
    this.hotelService.izmeniHotel(hotel, idHotela).subscribe(data => {
      this.router.navigateByUrl('hotelAdminPage');
    })
  }

}

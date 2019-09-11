import { Component, OnInit } from '@angular/core';
import {OcenaVoziloModel} from "../../model/ocenaVozilo.model";
import {OcenaHotelModel} from "../../model/ocenaHotel.model";
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {OcenaVoziloService} from "../../service/ocenaVozilo.service";
import {OcenaHotelService} from "../../service/ocenaHotel.service";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-hotel-rating',
  templateUrl: './hotel-rating.component.html',
  styleUrls: ['./hotel-rating.component.css']
})
export class HotelRatingComponent implements OnInit {

  public form: FormGroup;
  public rating: AbstractControl;

  constructor(protected router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              private ocenaService: OcenaHotelService,
              private authService: AuthService) {
    this.form = this.fb.group({
      'rating': ['', Validators.compose([Validators.required])],
    })
    this.rating = this.form.controls['rating'];
  }

  ngOnInit() {
  }

  logout()
  {
    this.authService.logOutUser();
  }

  confirmClick() {
    const idHotel = this.route.snapshot.params.idHotel;
    const object = new OcenaHotelModel(
      this.rating.value
    );
    this.ocenaService.oceniHotel(object, idHotel).subscribe(data => {
      this.router.navigateByUrl('registeredUserPage');

    })

  }
  exit(){
    this.router.navigateByUrl('registeredUserPage');
  }

}

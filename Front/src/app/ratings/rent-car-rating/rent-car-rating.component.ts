import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {OcenaRentService} from "../../service/ocenaRent.service";
import {OcenaVoziloModel} from "../../model/ocenaVozilo.model";
import {OcenaRentModel} from "../../model/ocenaRent.model";

@Component({
  selector: 'app-rent-car-rating',
  templateUrl: './rent-car-rating.component.html',
  styleUrls: ['./rent-car-rating.component.css']
})
export class RentCarRatingComponent implements OnInit {
  public form: FormGroup;
  public rating: AbstractControl;

  constructor(protected router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              private ocenaService: OcenaRentService) {
    this.form = this.fb.group({
      'rating': ['', Validators.compose([Validators.required])],
    })
    this.rating = this.form.controls['rating'];
  }

  ngOnInit() {
  }

  confirmClick() {
    const idRent = this.route.snapshot.params.idRent;
    const object = new OcenaRentModel(
      this.rating.value
    );
    this.ocenaService.oceniRent(object, idRent).subscribe(data => {
      this.router.navigateByUrl('registeredUserPage');
    })

  }
  exit(){
    this.router.navigateByUrl('registeredUserPage');
  }

}

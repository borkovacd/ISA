import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {OcenaSobaModel} from "../../model/ocenaSoba.model";
import {OcenaSobaService} from "../../service/ocenaSoba.service";
import {OcenaVoziloModel} from "../../model/ocenaVozilo.model";

@Component({
  selector: 'app-room-rating',
  templateUrl: './room-rating.component.html',
  styleUrls: ['./room-rating.component.css']
})
export class RoomRatingComponent implements OnInit {

  public form: FormGroup;
  public rating: AbstractControl;

  constructor(protected router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              public ocenaService: OcenaSobaService) {
    this.form = this.fb.group({
      'rating': ['', Validators.compose([Validators.required])],
    })
    this.rating = this.form.controls['rating'];
  }

  ngOnInit() {
  }

  confirmClick() {
    const idSoba = this.route.snapshot.params.idSoba;
    const object = new OcenaSobaModel(
      this.rating.value
    );
    this.ocenaService.oceniSobu(object, idSoba).subscribe(data => {
      this.router.navigateByUrl('registeredUserPage');

    })

  }
  exit(){
    this.router.navigateByUrl('registeredUserPage');
  }

}

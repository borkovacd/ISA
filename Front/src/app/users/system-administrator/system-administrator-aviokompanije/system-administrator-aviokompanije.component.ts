import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../service/user.service';
import {AviokompanijaService} from '../../../service/aviokompanija.service';
import {HotelModel} from '../../../model/hotel.model';
import {AviokompanijaModel} from '../../../model/aviokompanija.model';

@Component({
  selector : 'system-administrator-aviokompanije',
  templateUrl : './system-administrator-aviokompanije.component.html',
  styleUrls   : ['./system-administrator-aviokompanije.component.scss']
})
export class SystemAdministratorAviokompanijeComponent implements  OnInit {

  public form: FormGroup;
  public name: AbstractControl;
  public address: AbstractControl;
  public description: AbstractControl;
  public administratorAviokompanije: AbstractControl;

  administratoriAviokompanija = []

  constructor(protected  router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              public aviokompanijaService: AviokompanijaService,
              public userService: UserService) {
    this.form = this.fb.group({
      'name': ['', Validators.compose([Validators.required])],
      'address': ['', Validators.compose([Validators.required])],
      'description': ['', Validators.compose([Validators.required])],
      'administratorAviokompanije': ['', Validators.compose([Validators.required])]
    })

    this.name = this.form.controls['name'];
    this.address = this.form.controls['address'];
    this.description = this.form.controls['description'];
    this.administratorAviokompanije = this.form.controls['administratorAviokompanije'];

  }

  public ngOnInit() {
    this.userService.getAviokompanijaAdministrators().subscribe(data => {
      this.administratoriAviokompanija = data;
    })
  }

  confirmClick() {
    this.registerAviokompaniju();
  }

  registerAviokompaniju() {
    const aviokompanija = new AviokompanijaModel(
      this.name.value,
      this.address.value,
      this.description.value,
      this.administratorAviokompanije.value,
    );

    this.aviokompanijaService.registerAviokompanija(aviokompanija).subscribe(data => {
      this.router.navigateByUrl('administratorPage');
    })
  }

  exit() {
    this.router.navigateByUrl('/welcomepage');
  }

}

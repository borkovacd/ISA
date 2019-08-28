import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../service/room.service';
import {RoomModel} from '../../model/room.model';
import {AdditionalServiceModel} from '../../model/additionalService.model';
import {AdditionalServiceService} from '../../service/additionalService.service';

@Component({
  selector: 'app-add-additional-service',
  templateUrl: './add-additional-service.component.html',
  styleUrls: ['./add-additional-service.component.css']
})
export class AddAdditionalServiceComponent implements OnInit {

  public form: FormGroup;
  public tipDodatneUsluge: AbstractControl;
  naslovStranice: string;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private additionalServiceService: AdditionalServiceService){
    this.form = this.fb.group({
      'tipDodatneUsluge': ['', Validators.compose([Validators.required])],
    })
    this.tipDodatneUsluge = this.form.controls['tipDodatneUsluge'];
  }

  ngOnInit() {

  }

  confirmClick() {
    this.createAdditionalService();
  }

  createAdditionalService() {
    const idHotela = this.route.snapshot.params.idHotela;

    const additionalService = new AdditionalServiceModel(
      this.tipDodatneUsluge.value
    );
    this.additionalServiceService.createAdditionalService(additionalService, idHotela).subscribe(data => {
      this.router.navigateByUrl('hotelAdminPage/additionalServices/' +  idHotela);
    })
  }

  exit() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/additionalServices/' +  idHotela);
  }
}


import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {LokacijaService} from "../../service/lokacija.service";
import {LokacijaModel} from "../../model/lokacija.model";
import {RoomModel} from "../../model/room.model";

@Component({
  selector: 'app-add-edit-filijala',
  templateUrl: './add-edit-filijala.component.html',
  styleUrls: ['./add-edit-filijala.component.css']
})
export class AddEditFilijalaComponent implements OnInit {

  public form: FormGroup;

  public adresa: AbstractControl;
  public drzava: AbstractControl;
  public grad: AbstractControl;
  public longitude: AbstractControl;
  public latitude: AbstractControl;

  naslovStranice: string;
  public method_name = 'DODAJ';


  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private lokacijaService: LokacijaService) {

    this.form = this.fb.group({
      'adresa': ['', Validators.compose([Validators.required])],
      'drzava': ['', Validators.compose([Validators.required])],
      'grad': ['', Validators.compose([Validators.required])],
      'longitude': ['', Validators.compose([Validators.required])],
      'latitude': ['', Validators.compose([Validators.required])],

    })
    this.adresa = this.form.controls['adresa'];
    this.drzava = this.form.controls['drzava'];
    this.grad = this.form.controls['grad'];
    this.longitude = this.form.controls['longitude'];
    this.latitude = this.form.controls['latitude'];
  }

  ngOnInit() {

    const mode = this.route.snapshot.params.mode;
    const idFilijala = this.route.snapshot.params.idFilijala;
    const idRent = this.route.snapshot.params.idRent;

    if (mode == 'edit') {
      this.method_name = 'IZMENI';
      this.naslovStranice = 'Izmena filijale';
      this.lokacijaService.vratiJednuFilijalu(idRent, idFilijala).subscribe(data => {
        this.form.controls['adresa'].setValue(data.adresa);
        this.form.controls['drzava'].setValue(data.drzava);
        this.form.controls['grad'].setValue(data.grad);
        this.form.controls['longitude'].setValue(data.longitude);
        this.form.controls['latitude'].setValue(data.latitude);

      })
    } else if (mode == 'add') {
      this.method_name = 'DODAJ';
      this.naslovStranice = 'Dodavanje filijale';
    }
  }

  confirmClick() {
    if (this.method_name === 'DODAJ') {
      this.createFilijala();
    } else {
      this.editFilijala();
    }
  }

  createFilijala()
  {
    const idRent = this.route.snapshot.params.idRent ;

    const filijala = new LokacijaModel(
      this.adresa.value,
      this.drzava.value,
      this.grad.value,
      this.longitude.value,
      this.latitude.value
    );

    this.lokacijaService.dodajFilijalu(filijala, idRent).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage/filijale/' + idRent);
    })
  }

  editFilijala()
  {
    const idRent = this.route.snapshot.params.idRent ;
    const idFilijala = this.route.snapshot.params.idFilijala ;

    const filijala = new LokacijaModel(
      this.adresa.value,
      this.drzava.value,
      this.grad.value,
      this.longitude.value,
      this.latitude.value
    );

    this.lokacijaService.izmeniFilijalu(filijala, idRent, idFilijala).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage/filijale/' + idRent);
    })
  }

  exit() {
    const idRent = this.route.snapshot.params.idRent;
    this.router.navigateByUrl('rentAdminPage/filijale/' +  idRent);
  }

}

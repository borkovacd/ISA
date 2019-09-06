import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from "../../service/vozilo.service";
import {VoziloModel} from "../../model/vozilo.model";

@Component({
  selector: 'app-add-edit-vozilo',
  templateUrl: './add-edit-vozilo.component.html',
  styleUrls: ['./add-edit-vozilo.component.css']
})
export class AddEditVoziloComponent implements OnInit {

  public form: FormGroup;

  public naziv: AbstractControl;
  public marka: AbstractControl;
  public model: AbstractControl;
  public godinaProizvodnje : AbstractControl;
  public brojSedista : AbstractControl;
  public tip: AbstractControl;

  naslovStranice: string;
  public method_name = 'DODAJ';

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private voziloService: VoziloService,) {

    this.form = this.fb.group({
      'brojSedista': ['', Validators.compose([Validators.required, Validators.pattern('^-?[0-9]{1,3}$')])],
      'godinaProizvodnje': ['', Validators.compose([Validators.required, Validators.pattern('^-?[0-9]{1,10}$')])],
      'naziv': ['', Validators.compose([Validators.required])],
      'marka': ['', Validators.compose([Validators.required])],
      'model': ['', Validators.compose([Validators.required])],
      'tip': ['', Validators.compose([Validators.required])]
    })
    this.brojSedista = this.form.controls['brojSedista'];
    this.godinaProizvodnje = this.form.controls['godinaProizvodnje'];
    this.naziv = this.form.controls['naziv'];
    this.marka = this.form.controls['marka'];
    this.model = this.form.controls['model'];
    this.tip = this.form.controls['tip'];
  }

  ngOnInit() {


    const mode = this.route.snapshot.params.mode;
    const idVozilo = this.route.snapshot.params.idVozilo;
    const idRent = this.route.snapshot.params.idRent;

    if (mode == 'edit') {
      this.method_name = 'IZMENI';
      this.naslovStranice = 'Izmena vozila';

      this.voziloService.vratiJednoVozilo(idRent, idVozilo).subscribe(data => {
        this.form.controls['cena'].setValue(data.cena);
        this.form.controls['brojSedista'].setValue(data.brojSedista);
        this.form.controls['godinaProizvodnje'].setValue(data.godinaProizvodnje);
        this.form.controls['tip'].setValue(data.tip);
        this.form.controls['naziv'].setValue(data.naziv);
        this.form.controls['marka'].setValue(data.marka);
        this.form.controls['model'].setValue(data.model);

      })
    } else if (mode == 'add') {
      this.method_name = 'DODAJ';
      this.naslovStranice = 'Dodavanje vozila';
    }
  }

  confirmClick() {
    if (this.method_name === 'DODAJ') {
      this.createVozilo();
    } else {
      this.editVozilo();
    }
  }

  createVozilo()
  {
    const idRent = this.route.snapshot.params.idRent ;
    const vozilo = new VoziloModel(
      this.naziv.value,
      this.marka.value,
      this.model.value,
      this.godinaProizvodnje.value,
      this.brojSedista.value,
      this.tip.value);

    this.voziloService.dodajVozilo(vozilo, idRent).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage/vozila/' + idRent);
    })
  }

  editVozilo()
  {

    const idRent = this.route.snapshot.params.idRent ;
    const idVozilo = this.route.snapshot.params.idVozilo;

    const vozilo = new VoziloModel(
                  this.naziv.value, this.marka.value, this.model.value, this.godinaProizvodnje.value,
      this.brojSedista.value, this.tip.value);

    this.voziloService.izmeniVozilo(vozilo, idRent, idVozilo).subscribe(data =>
    {
      this.router.navigateByUrl('rentAdminPage/vozila/' + idRent);
    })
  }

  exit()
  {
    const idRent = this.route.snapshot.params.idRent ;
    this.router.navigateByUrl('rentAdminPage/vozila/' + idRent);
  }

}

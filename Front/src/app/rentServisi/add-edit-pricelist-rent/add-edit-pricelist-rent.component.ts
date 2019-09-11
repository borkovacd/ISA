import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {PricelistRentService} from "../../service/pricelistRent.service";
import {PricelistRentModel} from "../../model/pricelistRent.model";
import {PricelistModel} from "../../model/pricelist.model";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-add-edit-pricelist-rent',
  templateUrl: './add-edit-pricelist-rent.component.html',
  styleUrls: ['./add-edit-pricelist-rent.component.css']
})
export class AddEditPricelistRentComponent implements OnInit {


  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  d1: any;
  d2: any;
  naslovStranice: string;

  public method_name = 'DODAJ';


  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private pricelistRentService: PricelistRentService,
              private authService: AuthService) {
    this.form = this.fb.group({
      'startDate': ['', Validators.compose([Validators.required])],
      'endDate': ['', Validators.compose([Validators.required])],
    })
    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
  }

  ngOnInit() {

    const mode = this.route.snapshot.params.mode;
    const idRent = this.route.snapshot.params.idRent;

    if (mode == 'edit') {
      this.method_name = 'IZMENI';
      this.naslovStranice = 'Izmena cenovnika';
      this.pricelistRentService.getPricelistRent(idRent).subscribe(data => {
        this.form.controls['startDate'].setValue(data.pocetakVazenja);
        this.form.controls['endDate'].setValue(data.prestanakVazenja);
      })
    } else if (mode == 'add') {
      this.method_name = 'DODAJ';
      this.naslovStranice = 'Dodavanje cenovnika';
    }
  }

  confirmClick() {
    if (this.method_name === 'DODAJ') {
      this.createPricelistRent();
    } else {
      this.editPricelistRent();
    }
  }

  logout()
  {
    this.authService.logOutUser();
  }

  exit() {
    const idRent = this.route.snapshot.params.idRent;
    this.router.navigateByUrl('rentAdminPage/pricelistsRent/' +  idRent);
  }

  private createPricelistRent() {
    const idRent = this.route.snapshot.params.idRent;

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const pricelist = new PricelistModel(
      this.d1,
      this.d2
    );
    this.pricelistRentService.createPricelistRent(pricelist, idRent).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage/pricelistsRent/' +  idRent);
    },

      error => {
      alert('Vec postoji kreiran cenovnik za taj vremenski period!');
      });

  }

  private editPricelistRent() {

  }



}

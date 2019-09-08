import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RentCarService} from "../../service/rentcar.service";
import {Observable} from 'rxjs';
import {AbstractControl, FormGroup, FormBuilder, Validators} from "@angular/forms";
import {SearchRentsModel} from "../../model/searchRents.model";


@Component({
  selector: 'app-welcome-page-servisi',
  templateUrl: './welcome-page-servisi.component.html',
  styleUrls: ['./welcome-page-servisi.component.css']
})
export class WelcomePageServisiComponent implements OnInit {

  rents = [];
  pretraga: boolean;

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  public rentName: AbstractControl;
  public rentLocation: AbstractControl;
  d1: any;
  d2: any;

  constructor(protected router: Router, public fb: FormBuilder,
              private route: ActivatedRoute, private rentService: RentCarService) {
    // pretraga rent servisa

    this.form = this.fb.group({
      'startDate': ['', Validators.compose([Validators.required])],
      'endDate': ['', Validators.compose([Validators.required])],
      'rentName': [''],
      'rentLocation': [''],
    })

    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
    this.rentName = this.form.controls['rentName'];
    this.rentLocation = this.form.controls['rentLocation'];
  }

  ngOnInit() {
    this.pretraga = false ;
    this.rentService.getAllRents().subscribe(data => {
      this.rents = data ;
    });
  }

  pretraziRentServise()
  {
    this.pretraga = true ;
  }

  pregledVozila(idRent: any)
  {
    this.router.navigateByUrl('welcomepage/vozila/' + idRent);
  }

  pregledFilijala(idRent: any)
  {
    this.router.navigateByUrl('welcomepage/filijale/' + idRent);

  }

  pregledCenovnika(idRent: any)
  {
    this.router.navigateByUrl('welcomepage/pricelistRent/' + idRent);
  }

  confirmClick() {
    if (this.startDate.value > this.endDate.value ) {
      alert('Uneti datum nije validan. Pokušajte ponovo.');
      this.startDate.reset();
      this.endDate.reset();
      return;
    }

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const searchRents = new SearchRentsModel (
      this.rentName.value,
      this.rentLocation.value,
      this.d1,
      this.d2
    );

    this.rentService.searchRents(searchRents).subscribe(data => {
      this.rents = data;
      this.pretraga = false;
    });
  }

}

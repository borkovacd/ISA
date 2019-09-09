import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {VoziloService} from "../../../service/vozilo.service";
import {TimePeriodModel} from "../../../model/timePeriod.model";
import {SearchRentsModel} from "../../../model/searchRents.model";
import {RentCarService} from "../../../service/rentcar.service";

@Component({
  selector: 'app-registered-user-servisi',
  templateUrl: './registered-user-servisi.component.html',
  styleUrls: ['./registered-user-servisi.component.css']
})
export class RegisteredUserServisiComponent implements OnInit {

  rents = [];
  pretraga: boolean;

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  public rentName: AbstractControl;
  public rentLocation: AbstractControl;
  d1: any;
  d2: any;

  public tipSort: AbstractControl;

  constructor(protected router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private rentService: RentCarService) {

    // pretraga rent servisa

    this.form = this.fb.group({
      'startDate': ['', Validators.compose([Validators.required])],
      'endDate': ['', Validators.compose([Validators.required])],
      'rentName': [''],
      'rentLocation': [''],
      'tipSort': [''],

    })

    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
    this.rentName = this.form.controls['rentName'];
    this.rentLocation = this.form.controls['rentLocation'];
    this.tipSort = this.form.controls['tipSort'];

  }

  ngOnInit() {

    this.pretraga = false ;
    this.rentService.getAllRents().subscribe(data => {
      this.rents = data ;
    })
  }

  // nakon sto se izabere rent prelazi na stranicu sa vozilima tog rent-servisa
  izaberiRent(id: any)
  {
    this.router.navigateByUrl('registeredUserPage/vozila/' + id);
  }

  // ukoliko nece da izabere servis iz liste, vec hoce da dodje do njega pretragom
  pretraziRentServise()
  {
    this.pretraga = true ;
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

  goBack() {
    this.router.navigateByUrl('registeredUserPage');
  }

  // sortiranje
  sortirajRent(){
    let sort : string = this.tipSort.value;
    this.rentService.sortRent(sort).subscribe(
      data => {
        this.rents = data;
      },
      error => {
        console.log(error);
      }
    )
  }




}

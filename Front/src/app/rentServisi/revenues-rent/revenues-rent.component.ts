import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {RentCarService} from "../../service/rentcar.service";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-revenues-rent',
  templateUrl: './revenues-rent.component.html',
  styleUrls: ['./revenues-rent.component.css']
})
export class RevenuesRentComponent implements OnInit {

  public form: FormGroup;
  d1: AbstractControl;
  d2: AbstractControl;
  nazivRent: string;
  revenueValue: number;
  showValue: boolean;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              public fb: FormBuilder,
              private rentService: RentCarService,
              private authService: AuthService) {
    this.form = this.fb.group({
      'd1': ['', Validators.required],
      'd2': ['', Validators.required],
    });
    this.d1 = this.form.controls['d1'];
    this.d2 = this.form.controls['d2'];
  }

  ngOnInit() {
    this.showValue = false;

    const idRent = this.route.snapshot.params.idRent;

    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv;
    })
  }

  confirm() {

    if(this.d1.value >= this.d2.value) {
      alert('Neispravan unos početnog i krajnjeg datuma. Pokušajte ponovo!');
      this.revenueValue = 0;
      this.showValue = false;
    } else {
      const idRent = this.route.snapshot.params.idRent;
      this.rentService.getRevenuesRent(idRent, this.d1.value, this.d2.value).subscribe(data => {
        this.revenueValue = data;
        this.showValue = true;
      });
    }
  }

  logout()
  {
    this.authService.logOutUser();
  }

  goBack() {
    this.router.navigateByUrl('rentAdminPage' );

  }


}

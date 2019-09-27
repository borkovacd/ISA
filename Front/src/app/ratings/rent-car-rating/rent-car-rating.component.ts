import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {OcenaRentService} from "../../service/ocenaRent.service";
import {OcenaVoziloModel} from "../../model/ocenaVozilo.model";
import {OcenaRentModel} from "../../model/ocenaRent.model";
import {AuthService} from "../../service/auth.service";
import {RentCarService} from "../../service/rentcar.service";
import {UserService} from "../../service/user.service";

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
              private ocenaService: OcenaRentService,
              private authService: AuthService,
              private rentService: RentCarService,
              private userService: UserService) {

    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["rentAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate([""]);
        } else if(data.uloga == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["systemAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_HOTELA"){
          this.router.navigate(["hotelAdminPage"]);
        }

      },

      error => {
        this.router.navigate(["prijava"]);
      }
    )

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
      this.rentService.getRent(idRent).subscribe( data => {
        data.ocena = this.rating.value ;
      })
      this.router.navigateByUrl('registeredUserPage');
    })

  }
  exit(){
    this.router.navigateByUrl('registeredUserPage');
  }

  logout()
  {
    this.authService.logOutUser();
  }

}

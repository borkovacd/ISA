import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {OcenaVoziloService} from "../../service/ocenaVozilo.service";
import {OcenaVoziloModel} from "../../model/ocenaVozilo.model";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-vozilo-rating',
  templateUrl: './vozilo-rating.component.html',
  styleUrls: ['./vozilo-rating.component.css']
})
export class VoziloRatingComponent implements OnInit {

  public form: FormGroup;
  public rating: AbstractControl;

  constructor(protected router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              private ocenaService: OcenaVoziloService,
              private authService: AuthService,
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
    const idVozilo = this.route.snapshot.params.id;
    const object = new OcenaVoziloModel(
      this.rating.value
    );
    this.ocenaService.oceniVozilo(object, idVozilo).subscribe(data => {
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

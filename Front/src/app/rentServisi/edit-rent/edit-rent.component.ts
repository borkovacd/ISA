import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {RentCarService} from '../../service/rentcar.service';
import {UserService} from '../../service/user.service';
import {RentCarModel} from '../../model/rentcar.model';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-edit-rent',
  templateUrl: './edit-rent.component.html',
  styleUrls: ['./edit-rent.component.css']
})
export class EditRentComponent implements OnInit {

  public form: FormGroup;
  public name: AbstractControl;
  public address: AbstractControl;
  public description: AbstractControl;
  public administratorRent: any;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              public rentService: RentCarService,
              public userService: UserService,
              private authService: AuthService) {


    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "OBICAN_KORISNIK"){
          this.router.navigate(["registeredUserPage"]);
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
      'name': ['', Validators.compose([Validators.required])],
      'address': ['', Validators.compose([Validators.required])],
      'description': [''],
    });

    this.name = this.form.controls['name'];
    this.address = this.form.controls['address'];
    this.description = this.form.controls['description'];
  }

  ngOnInit() {

    const idRent = this.route.snapshot.params.idRent ;

    this.rentService.getRent(idRent).subscribe(data => {
      this.form.controls['name'].setValue(data.naziv);
      this.form.controls['address'].setValue(data.adresa);
      this.form.controls['description'].setValue(data.opis);
      this.administratorRent = data.administratorRentCar ;
    });
  }

  exit() {
    this.router.navigateByUrl('/rentAdminPage');
  }

  confirmClick() {
    this.editRent();
  }

  logout()
  {
    this.authService.logOutUser();
  }

  editRent() {
    let ocena: any = 0;
    const rent = new RentCarModel(
      this.name.value,
      this.address.value,
      this.description.value,
      '',
      ocena
    );

    const idRent = this.route.snapshot.params.idRent;
    this.rentService.izmeniRent(rent, idRent).subscribe(data => {
      this.router.navigateByUrl('rentAdminPage');
    });
  }

}

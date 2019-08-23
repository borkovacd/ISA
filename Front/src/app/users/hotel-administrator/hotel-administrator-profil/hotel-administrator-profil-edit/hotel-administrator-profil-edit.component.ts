import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../../service/user.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {KorisnikProfilModel} from '../../../../model/korisnikProfil.model';

@Component({
  selector: 'app-hotel-administrator-profil-edit',
  templateUrl: './hotel-administrator-profil-edit.component.html',
  styleUrls: ['./hotel-administrator-profil-edit.component.css']
})
export class HotelAdministratorProfilEditComponent implements OnInit {

  public form: FormGroup;
  public ime: AbstractControl;
  public prezime: AbstractControl;
  public email: AbstractControl;
  public grad: AbstractControl;
  public telefon: AbstractControl;
  public korisnickoIme: AbstractControl;
  public lozinka: AbstractControl;
  public ponovljenaLozinka: AbstractControl;

  constructor(protected router: Router,
              private userService: UserService,
              public fb: FormBuilder
              ) {
    this.form = this.fb.group({
      'ime': ['', Validators.compose([Validators.required])],
      'prezime': ['', Validators.compose([Validators.required])],
      'email': ['', Validators.compose([Validators.required])],
      'grad': ['', Validators.compose([Validators.required])],
      'telefon': ['', Validators.compose([Validators.required])],
      'korisnickoIme': ['', Validators.compose([Validators.required])],
      'lozinka': ['', Validators.compose([Validators.required])],
      'ponovljenaLozinka': ['', Validators.compose([Validators.required])],
    })
    this.ime = this.form.controls['ime'];
    this.prezime = this.form.controls['prezime'];
    this.email = this.form.controls['email'];
    this.grad = this.form.controls['grad'];
    this.telefon = this.form.controls['telefon'];
    this.korisnickoIme = this.form.controls['korisnickoIme'];
    this.lozinka = this.form.controls['lozinka'];
    this.ponovljenaLozinka = this.form.controls['ponovljenaLozinka'];
  }

  ngOnInit() {
    this.userService.getKorisnikData().subscribe(data => {
      this.form.controls['ime'].setValue(data.ime);
      this.form.controls['prezime'].setValue(data.prezime);
      this.form.controls['email'].setValue(data.email);
      this.form.controls['grad'].setValue(data.grad);
      this.form.controls['telefon'].setValue(data.telefon);
      this.form.controls['korisnickoIme'].setValue(data.korisnickoIme);
      this.form.controls['lozinka'].setValue(data.lozinka);
      this.form.controls['ponovljenaLozinka'].setValue(data.lozinka);
    });
  }

  saveChanges() {
    this.editUser();
  }

  editUser() {
    const user = new KorisnikProfilModel (
      this.ime.value,
      this.prezime.value,
      this.lozinka.value,
      this.ponovljenaLozinka.value,
      this.email.value,
      this.telefon.value,
      this.grad.value,
    );

    this.userService.editUser(user).subscribe(data => {
      this.redirectTo('/hotelAdminPage');
    });
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }
}

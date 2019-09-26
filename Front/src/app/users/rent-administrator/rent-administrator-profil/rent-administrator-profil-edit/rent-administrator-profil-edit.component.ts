import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../../service/user.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {KorisnikProfilModel} from '../../../../model/korisnikProfil.model';

@Component({
  selector: 'app-rent-administrator-profil-edit',
  templateUrl: './rent-administrator-profil-edit.component.html',
  styleUrls: ['./rent-administrator-profil-edit.component.css']
})
export class RentAdministratorProfilEditComponent implements OnInit {

  public form: FormGroup;
  public ime: AbstractControl;
  public prezime: AbstractControl;
  public grad: AbstractControl;
  public telefon: AbstractControl;
  public lozinka: AbstractControl;
  public ponovljenaLozinka: AbstractControl;
  public passwordsMatch : boolean = false ;

  constructor(protected router: Router,
              private userService: UserService,
              public fb: FormBuilder)
  {
    this.form = this.fb.group({
      'ime': ['', Validators.compose([Validators.required])],
      'prezime': ['', Validators.compose([Validators.required])],
      'grad': ['', Validators.compose([Validators.required])],
      'telefon': ['', Validators.compose([Validators.required, Validators.pattern('^-?[0-9]{1,10}$')])],
      'lozinka': ['', Validators.compose([Validators.required])],
      'ponovljenaLozinka': ['', Validators.compose([Validators.required])],
    })

    this.ime = this.form.controls['ime'];
    this.prezime = this.form.controls['prezime'];
    this.grad = this.form.controls['grad'];
    this.telefon = this.form.controls['telefon'];
    this.lozinka = this.form.controls['lozinka'];
    this.ponovljenaLozinka = this.form.controls['ponovljenaLozinka'];

  }

  ngOnInit()
  {
    this.userService.getCurrentUser().subscribe(data => {
      this.form.controls['ime'].setValue(data.ime);
      this.form.controls['prezime'].setValue(data.prezime);
      this.form.controls['grad'].setValue(data.grad);
      this.form.controls['telefon'].setValue(data.telefon);
      this.form.controls['lozinka'].setValue(data.lozinka);
      this.form.controls['ponovljenaLozinka'].setValue(data.lozinka);
    });
  }

  checkPasswords() {
    if (this.lozinka.value == this.ponovljenaLozinka.value) {
      return true;
    }
    return false;
  }

  saveChanges() {
    this.passwordsMatch = this.checkPasswords();
    if(this.passwordsMatch == true) {
      this.editUser();
    } else {
      alert('Lozinke se ne poklapaju');
    }

  }

  editUser() {
    const user = new KorisnikProfilModel (
      this.ime.value,
      this.prezime.value,
      this.lozinka.value,
      this.ponovljenaLozinka.value,
      this.telefon.value,
      this.grad.value,
    );

    this.userService.editCurrentUser(user).subscribe(data => {
      this.redirectTo('/rentAdminPage');
    });
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }

}

import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import {NavigationEnd, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';


@Component({
  selector : 'system-administrator-administratori',
  templateUrl : './system-administrator-administratori.component.html',
  styleUrls   : ['./system-administrator-administratori.component.scss']
})
export class SystemAdministratorAdministratoriComponent implements  OnInit {

  regularUsers = []
  administrators: [];
  promenaUloge: boolean;
  pu: any;
  novaUloga: AbstractControl;
  public form: FormGroup

  constructor(protected router: Router,
              public fb: FormBuilder,
              private userService: UserService,) {
    this.form = this.fb.group({
      'novaUloga': ['', Validators.compose([Validators.required])],
    })
    this.novaUloga = this.form.controls['novaUloga'];
  }

  public ngOnInit() {

    this.promenaUloge = false;

    this.userService.getRegularUsers().subscribe(data => {
      this.regularUsers = data;
    });

    this.userService.getAdministrators().subscribe(data => {
      this.administrators = data;
    });


  }

  promeniUlogu(u: any) {
    this.promenaUloge = true;
    this.pu = u;
  }

  ukloniKorisnika(korisnickoIme: any) {

  }

  potvrdiPromenu(pu: any, novaUloga: string) {
    this.userService.changeRole(pu.id, novaUloga).subscribe(data => {
      this.redirectTo('/systemAdminPage');
    });
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }
}

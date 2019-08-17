import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import {Router} from '@angular/router';

@Component({
  selector : 'system-administrator-administratori',
  templateUrl : './system-administrator-administratori.component.html',
  styleUrls   : ['./system-administrator-administratori.component.scss']
})
export class SystemAdministratorAdministratoriComponent implements  OnInit {

  regularUsers = []

  constructor(protected router: Router,
              private userService: UserService ) { }

  public ngOnInit() {

    this.userService.getRegularUsers().subscribe(data => {
      this.regularUsers = data;
    });


  }

  promeniUlogu(korisnickoIme: any) {
    
  }

  ukloniKorisnika(korisnickoIme: any) {
    
  }
}

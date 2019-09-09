import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../service/user.service';
@Component({
  selector : 'hotel-administrator-profil',
  templateUrl : './hotel-administrator-profil.component.html',
  styleUrls   : ['./hotel-administrator-profil.component.scss']
})
export class HotelAdministratorProfilComponent implements  OnInit {

  constructor(protected router: Router,
              private userService: UserService) { }

  korisnik: any = null;

  public ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.korisnik = data;
    });
  }
}

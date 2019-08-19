import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../service/user.service';
@Component({
  selector : 'hotel-administrator-profil',
  templateUrl : './hotel-administrator-profil.component.html',
  styleUrls   : ['./hotel-administrator-profil.component.scss']
})
export class HotelAdministratorProfilComponent implements  OnInit {

  korisnik: any = null;

  constructor(protected router: Router,
              private userService: UserService) { }

  public ngOnInit() {

    this.userService.getKorisnikData().subscribe(data => {
      this.korisnik = data;
    });
  }

  azurirajPodatke(id: any) {

  }
}

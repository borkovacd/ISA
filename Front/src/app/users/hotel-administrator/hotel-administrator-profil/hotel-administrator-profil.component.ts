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

  activeTab = 'profile';

  public ngOnInit() {

    this.userService.getKorisnikData().subscribe(data => {
      this.korisnik = data;
    });
  }

  azurirajPodatke(id: any) {

  }

  search(activeTab) {
    this.activeTab = activeTab;
  }

  result(activeTab) {
    this.activeTab = activeTab;
  }
}

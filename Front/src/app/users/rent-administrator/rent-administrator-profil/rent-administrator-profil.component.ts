import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../service/user.service';

@Component({
  selector: 'app-rent-administrator-profil',
  templateUrl: './rent-administrator-profil.component.html',
  styleUrls: ['./rent-administrator-profil.component.css']
})
export class RentAdministratorProfilComponent implements OnInit {

  korisnik: any = null;

  constructor(protected router: Router,
              private userService: UserService) { }


  ngOnInit() {
    this.userService.getKorisnikData().subscribe(data => {
      this.korisnik = data;
    });
  }

}

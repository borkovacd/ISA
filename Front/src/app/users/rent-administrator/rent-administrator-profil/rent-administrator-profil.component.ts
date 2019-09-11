import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../service/user.service';
import {AuthService} from "../../../service/auth.service";

@Component({
  selector: 'app-rent-administrator-profil',
  templateUrl: './rent-administrator-profil.component.html',
  styleUrls: ['./rent-administrator-profil.component.css']
})
export class RentAdministratorProfilComponent implements OnInit {

  korisnik: any = null;

  constructor(protected router: Router,
              private userService: UserService,
              private authService: AuthService) { }


  ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.korisnik = data;
    });
  }


}

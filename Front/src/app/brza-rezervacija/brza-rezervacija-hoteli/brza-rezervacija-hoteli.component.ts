import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-brza-rezervacija-hoteli',
  templateUrl: './brza-rezervacija-hoteli.component.html',
  styleUrls: ['./brza-rezervacija-hoteli.component.css']
})
export class BrzaRezervacijaHoteliComponent implements OnInit {

  constructor(private userService : UserService,
              private router: Router,
              private authService: AuthService) { }

  ngOnInit() {
  }

  logout()
  {
    this.authService.logOutUser();
  }

  rezervisi() {
    const idRezervacije = 1; //koristice se prosledjen id rezervacije leta
    this.router.navigateByUrl('brzaRezervacija/hoteli/1/izbor');

  }

  preskoci() {
    const idRezervacije = 1; //koristice se prosledjen id rezervacije leta
    this.router.navigateByUrl('brzaRezervacija/rentServisi/1');
  }
}

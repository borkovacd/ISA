import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-brza-rezervacija-rent-servisi',
  templateUrl: './brza-rezervacija-rent-servisi.component.html',
  styleUrls: ['./brza-rezervacija-rent-servisi.component.css']
})
export class BrzaRezervacijaRentServisiComponent implements OnInit {

  constructor(private userService : UserService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit() {
  }

  logout()
  {
    this.authService.logOutUser();
  }

  rezervisi() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/rentServisi/' + idRezervacijeLeta + '/izbor');

  }

  preskoci() {
    this.router.navigateByUrl('registeredUserPage');
  }

}

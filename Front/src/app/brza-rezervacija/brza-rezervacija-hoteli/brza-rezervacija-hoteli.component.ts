import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-brza-rezervacija-hoteli',
  templateUrl: './brza-rezervacija-hoteli.component.html',
  styleUrls: ['./brza-rezervacija-hoteli.component.css']
})
export class BrzaRezervacijaHoteliComponent implements OnInit {

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
    this.router.navigateByUrl('brzaRezervacija/hoteli/' + idRezervacijeLeta + '/izbor');

  }

  preskoci() {
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/rentServisi/' + idRezervacijeLeta);
  }
}

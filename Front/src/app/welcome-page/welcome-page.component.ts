import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector : 'welcome-page',
  templateUrl : './welcome-page.component.html',
  styleUrls   : ['./welcome-page.component.scss']
})
export class WelcomePageComponent implements  OnInit{

  constructor(protected router: Router) {

  }

  public ngOnInit() {
  }

  ulogujSe() {
    this.router.navigateByUrl('/prijava');
  }

  registrujSe() {
    this.router.navigateByUrl('/registracija');
  }
}

import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {KorisnikModel} from '../model/Korisnik.model';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {AuthService} from '../service/auth.service';
import * as $ from 'jQuery' ;

@Component({
  selector: 'app-promena-lozinke',
  templateUrl: './promena-lozinke.component.html',
  styleUrls: ['./promena-lozinke.component.css']
})
export class PromenaLozinkeComponent implements OnInit {

  loginForm: FormGroup;

  korisnik: KorisnikModel = new KorisnikModel();
  errorMessage: string = '';
  poruka = '';
  ponovljenaLozinka = '';
  lozinke = '';

  trenutniKorisnik: KorisnikModel = new KorisnikModel();

  constructor(private authService: AuthService, private http: HttpClient, private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  potvrdi() {

    let provera = false;

    if (this.korisnik.lozinka == '' || this.korisnik.lozinka == undefined) {
      $('#passValue').addClass('border-danger');
      provera = true;
    } else {
      $('#passValue').removeClass('border-danger');
    }
    if (this.ponovljenaLozinka == '' || this.ponovljenaLozinka == undefined) {
      $('#passValue1').addClass('border-danger');
      provera = true;
    } else {
      $('#passValue1').removeClass('border-danger');
    }


    // lozinka i lozinka1 se moraju poklapati
    if (this.ponovljenaLozinka != this.korisnik.lozinka) {
      provera = true;
      this.lozinke = 'Unete lozinke se ne poklapaju!';
      $('#password').addClass('border-danger');
      $('#password1').addClass('border-danger');
    } else {
      this.lozinke = '';
      $('#password').removeClass('border-danger');
      $('#password1').removeClass('border-danger');
    }


    if(!provera) {
      this.authService.getCurrentUser().subscribe(
        data => {
          this.trenutniKorisnik = data;
          this.trenutniKorisnik.lozinka = this.korisnik.lozinka;
          this.userService.promeniLozinku(this.trenutniKorisnik).subscribe(
            data => {
              alert('Uspesno ste izmenili lozinku, mozete koristiti sajt');
              this.router.navigateByUrl("/welcomepage");
            },
            error => {
              this.poruka = "Uneli ste vasu postojecu lozinku!";
            }
          );
        }
      );
    }
  }

  otkazi()
  {
    this.router.navigateByUrl('/welcomepage');
  }

}

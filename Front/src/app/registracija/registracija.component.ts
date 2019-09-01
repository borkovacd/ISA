import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {KorisnikModel} from '../model/Korisnik.model';
import {UserService} from '../service/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from "@angular/router";
import * as $ from 'jQuery';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {

  registerForm: FormGroup;

  korisnik: KorisnikModel = new KorisnikModel();
  ponovljenaLozinka = "";
  lozinke = '';

  poruka = '';
  porukaTelefon = '';
  porukaIme = '';

  constructor(private http: HttpClient, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
  }

  submit() {
    let provera : boolean = false;

    if (this.korisnik.ime == "" || this.korisnik.ime == undefined) {
      $("#nameValue").addClass('border-danger');
      provera = true;
    } else {
      $('#nameValue').removeClass('border-danger');
    }
    if (this.korisnik.prezime == "" || this.korisnik.prezime == undefined) {
      $('#surname').addClass('border-danger');
      provera = true;
    } else {
      $('#surname').removeClass('border-danger');
    }
    if (this.korisnik.korisnickoIme == "" || this.korisnik.korisnickoIme == undefined) {
      $('#username').addClass('border-danger');
      provera = true;
    } else {
      $('#username').removeClass('border-danger');
    }
    if (this.korisnik.lozinka == "" || this.korisnik.lozinka == undefined) {
      $('#passValue').addClass('border-danger');
      provera = true;
    } else {
      $('#passValue').removeClass('border-danger');
    }
    if (this.ponovljenaLozinka == "" || this.ponovljenaLozinka == undefined) {
      $('#passValue1').addClass('border-danger');
      provera = true;
    } else {
      $('#passValue1').removeClass('border-danger');
    }
    if (this.korisnik.email == "" || this.korisnik.email == undefined) {
      $('#email').addClass('border-danger');
      provera = true;
    } else {
      $('#email').removeClass('border-danger');
    }
    if (this.korisnik.telefon == "" || this.korisnik.telefon == undefined) {
      $('#brTel').addClass('border-danger');
      provera = true;
    } else {
      $('#brTel').removeClass('border-danger');
    }
    if (this.korisnik.grad == "" || this.korisnik.grad == undefined) {
      $('#grad').addClass('border-danger');
      provera = true;
    } else {
      $('#grad').removeClass('border-danger');
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
    // u broj telefona smeju biti unesene samo cifre
    if (isNaN(+this.korisnik.telefon)) {
      provera = true;
      this.porukaTelefon = 'Dozvoljeno je uneti samo brojeve!';
      $('#brTel').addClass('border-danger');
    } else {
      this.porukaTelefon = '';
      $('#brTel').removeClass('border-danger');
    }


    if (!provera) {
      this.userService.register(this.korisnik).subscribe(
        data => {
          this.router.navigateByUrl('/welcomepage');
          this.poruka = 'Dobicete mail za verifikaciju, ulogujte se na svoj mail da ga potvrdite!';
          this.userService.verifikujNalog(data.email).subscribe(
            // tslint:disable-next-line:no-shadowed-variable
            data => {
              this.korisnik = new KorisnikModel();
              this.ponovljenaLozinka = '';
              this.poruka = '';
            }
          );
          alert('Prosla verifikacija!');
        },

        error => {
          this.poruka = 'Email ili korisnicko ime koje ste uneli vec postoje vezani za drugi korisnicki nalog!';

        }
      );
    }
  }
}

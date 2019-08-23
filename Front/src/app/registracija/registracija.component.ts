import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {KorisnikModel} from '../model/Korisnik.model';
import {UserService} from '../service/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {

  registerForm: FormGroup;

  korisnik: KorisnikModel = new KorisnikModel();
  ponovljenaLozinka = '';
  lozinke = '';

  poruka = '';
  porukaTelefon = '';

  constructor(private http: HttpClient, private userService: UserService, private router:Router) {
  }

  ngOnInit() {
  }

  submit() {
    const provera = false;

    /*
    if (this.korisnik.ime == '') {
      $('#name').addClass('border-danger');
      provera = true;
    } else {
      $('#name').removeClass('border-danger');
    }

    if (this.korisnik.prezime == '') {
      $('#surname').addClass('border-danger');
      provera = true;
    } else {
      $('#surname').removeClass('border-danger');
    }

    if (this.korisnik.korisnickoIme == '') {
      $('#username').addClass('border-danger');
      provera = true;
    } else {
      $('#username').removeClass('border-danger');
    }

    if (this.korisnik.lozinka == '') {
      $('#password').addClass('border-danger');
      provera = true;
    } else {
      $('#password').removeClass('border-danger');
    }

    if (this.ponovljenaLozinka == '') {
      $('#password1').addClass('border-danger');
      provera = true;
    } else {
      $('#password1').removeClass('border-danger');
    }

    if (this.korisnik.email == '') {
      $('#email').addClass('border-danger');
      provera = true;
    } else {
      $('#email').removeClass('border-danger');
    }

    if (this.korisnik.telefon == '') {
      $('#brTel').addClass('border-danger');
      provera = true;
    } else {
      $('#brTel').removeClass('border-danger');
    }

    if (this.korisnik.grad == '') {
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

     */

    if (!provera) {
      this.userService.register(this.korisnik).subscribe(
        data => {
          this.router.navigateByUrl('/welcomepage');
          this.poruka = 'Dobicete mail za verifikaciju, ulogujte se na svoj mail da ga potvrdite!';
          this.userService.verifikujNalog(data.email).subscribe(
            data => {
              this.korisnik = new KorisnikModel();
              this.ponovljenaLozinka = '';
              this.poruka = '';
            }
          );
          alert('Prosla verifikacija!');
        },

        error => {
          this.poruka = 'Email koji ste uneli vec postoji vezan za drugi korisnicki nalog!';
          alert('OOlga');
        }
      );
    }
  }
}

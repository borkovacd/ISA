import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {KorisnikModel} from '../model/Korisnik.model';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import * as $ from 'jQuery' ;

@Component({
  selector: 'app-prijava',
  templateUrl: './prijava.component.html',
  styleUrls: ['./prijava.component.css']
})
export class PrijavaComponent implements OnInit {

  loginForm: FormGroup;

  korisnik: KorisnikModel = new KorisnikModel();
  poruka = '';

  constructor(private http: HttpClient, private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    let provera : boolean = false;

    if(this.korisnik.email == ""){
      $("#emailValue").addClass('border-danger');
      provera = true;
    } else {
      $("#emailValue").removeClass('border-danger');
    }

    if(this.korisnik.lozinka == ""){
      $("#passValue").addClass('border-danger');
      provera = true;
    } else {
      $("#passValue").removeClass('border-danger');
    }

    if (!provera) {
      this.userService.logIn(this.korisnik).subscribe(
        data => {
          if (data.statusKorisnika == 'obican') {
            this.router.navigate(['/registrovaniKorisnik']);
          } else if (data.statusKorisnika == 'avio') {
            this.router.navigate(['/glavna']);
          }
          else if (data.statusKorisnika == 'hotel') {
            this.router.navigate(['/hotelAdminPage']);
          }
          else if (data.statusKorisnika == 'prvo') {
            this.router.navigate(['/promenaLozinke']);
          } else if (data.statusKorisnika == 'rent') {
            this.router.navigate(['/rentAdminPage']);
          } else if (data.statusKorisnika == 'sistem') {
            this.router.navigate(['/systemAdminPage']);
          } else if (data.statusKorisnika == 'greska') {
            this.poruka = 'Uneli ste neispravnu email adresu ili lozinku!';
          } else {
            this.poruka = 'Neophodno je verifikovati nalog da biste mogli da se ulogujete!';
          }
        }
      );
    }

  }

}

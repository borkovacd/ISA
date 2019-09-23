import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {KorisnikModel} from '../model/Korisnik.model';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {AuthService} from "../service/auth.service";
import * as $ from 'jQuery' ;

@Component({
  selector: 'app-prijava',
  templateUrl: './prijava.component.html',
  styleUrls: ['./prijava.component.css']
})
export class PrijavaComponent implements OnInit {

  loginForm: FormGroup;

  user : KorisnikModel = new KorisnikModel();
  errorMessage : String;

  korisnik: KorisnikModel = new KorisnikModel();
  poruka = '';

  constructor(private authService : AuthService, private http: HttpClient, private userService: UserService, private router: Router) { }

  ngOnInit() {
  }


  clickLogIn(){

    let provera : boolean = false;


    if(this.user.korisnickoIme == "" || this.user.korisnickoIme == undefined){
      $("#emailValue").addClass('border-danger');
      provera = true;
    } else {
      $("#emailValue").removeClass('border-danger');
    }

    if(this.user.lozinka == "" || this.user.lozinka == undefined){
      $("#passValue").addClass('border-danger');
      provera = true;
    } else {
      $("#passValue").removeClass('border-danger');
    }

    if(!provera) {
      this.authService.login(this.user).subscribe(
        success => {

          if (!success) {
            alert('Neispravno korisnicko ime ili lozinka!');
            this.poruka = "Neispravno korisnicko ime ili lozinka!";
          } else {
            this.authService.getCurrentUser().subscribe(
              data => {

                if (data.statusKorisnika == "prvo") {
                  alert("Nakon prvog logovanja morate promeniti lozinku!");
                  this.router.navigateByUrl('/promenaLozinke');
                }
                else if (data.statusKorisnika == "nijeVerifikovan") {
                  this.poruka = 'Neophodno je verifikovati nalog da biste mogli da se ulogujete!';
                }


                else {
                  alert('Vasa uloga je: ' + data.uloga);


                  localStorage.setItem("ROLE", data.uloga);
                  localStorage.setItem("USERNAME", data.korisnickoIme);

                  if (localStorage.getItem("ROLE") == "ADMINISTRATOR_SISTEMA") {
                    this.router.navigate(["/systemAdminPage"]);
                  } else if (localStorage.getItem("ROLE") == "ADMINISTRATOR_HOTELA") {
                    this.router.navigate(["/hotelAdminPage"])
                  } else if (localStorage.getItem("ROLE") == "ADMINISTRATOR_RENT_A_CAR") {
                    this.router.navigate(["/rentAdminPage"])
                  } else if (localStorage.getItem("ROLE") == "ADMINISTRATOR_AVIOKOMPANIJE") {
                    this.router.navigate(["/glavna"]);
                  } else if (localStorage.getItem("ROLE") == "OBICAN_KORISNIK") {
                    this.router.navigate(["/registeredUserPage"]);
                  }
                }

              }
            )
          }
        }
      )
    }
  }

}

import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup} from "@angular/forms";
import {AdditionalServiceService} from "../../../../service/additionalService.service";
import {HotelService} from "../../../../service/hotel.service";
import {AdditionalServicesReservationModel} from "../../../../model/additionalServicesReservation.model";
import {HotelReservationService} from "../../../../service/hotelReservation.service";
import {AuthService} from "../../../../service/auth.service";

@Component({
  selector: 'app-registered-user-hoteli-dodatne-usluge',
  templateUrl: './registered-user-hoteli-dodatne-usluge.component.html',
  styleUrls: ['./registered-user-hoteli-dodatne-usluge.component.css']
})
export class RegisteredUserHoteliDodatneUslugeComponent implements OnInit {

  nazivHotela: string;
  public form: FormGroup;
  services = [];
  listAditionalService = [];
  values = '';
  public aditional: AbstractControl;

  rezervacija: any;

  constructor(private userService: UserService,
              private hotelService: HotelService,
              private additionalServiceService: AdditionalServiceService,
              private hotelReservationService: HotelReservationService,
              private router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              private authService: AuthService) {
    this.form = this.fb.group({
          'aditional': ['']
    })
  this.aditional = this.form.controls['aditional'];
  }

  ngOnInit() {
    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

    const idRezervacije = this.route.snapshot.params.idRezervacije;
    this.hotelReservationService.getReservation(idRezervacije).subscribe(data => {
      this.rezervacija = data;
    })

    this.additionalServiceService.getAvailableAdditionalServices(idHotela, idRezervacije).subscribe(data => {
      this.services = data;
    })
  }

  public check = false;

  addService(service: any) {
    this.check = false;

    if (this.listAditionalService.length === 0) {
      this.listAditionalService.push(service);
      this.values += service + '    ';
    } else {


      for (var i = 0; i < this.listAditionalService.length; i++) {

        if (this.listAditionalService[i] == service) {
          this.check = true;
          break;
        }
      }

      if (this.check == false) {
        this.listAditionalService.push(service);
        this.values += service + '    ';

      }

    }
    console.log(this.listAditionalService)
  }

  removeService(service: any) {
    this.check = false;


    for (var i = 0; i < this.listAditionalService.length; i++) {

      if (this.listAditionalService[i] == service) {
        this.listAditionalService = this.listAditionalService.filter(item => item != service);
        break;
      }
    }
    this.values = '';
    for (var i = 0; i < this.listAditionalService.length; i++) {
      this.values += this.listAditionalService[i] + '    ';
    }
  }

  exit() {

    this.router.navigateByUrl('/registeredUserPage');
  }

  confirmClick() {
    const additionalForReservation = new AdditionalServicesReservationModel (
      this.rezervacija.datumPocetka,
      this.rezervacija.datumKraja,
      this.listAditionalService,

    );
    const idRezervacije = this.route.snapshot.params.idRezervacije;
    this.hotelReservationService.addToHotelReservation(additionalForReservation, idRezervacije).subscribe(data => {
      alert('Uspesna rezervacija');
      this.router.navigateByUrl('/registeredUserPage' );
    })
  }

  logout()
  {
    this.authService.logOutUser();
  }

}

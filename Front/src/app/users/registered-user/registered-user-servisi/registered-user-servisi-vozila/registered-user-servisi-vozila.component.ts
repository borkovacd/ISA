import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from '../../../../service/vozilo.service';
import {RentCarService} from '../../../../service/rentcar.service';
import {TimePeriodModel} from '../../../../model/timePeriod.model';
import {UserService} from '../../../../service/user.service';
import {CheckAvailabilityRentModel} from '../../../../model/checkAvailabilityRent.model';
import {SearchRentsModel} from '../../../../model/searchRents.model';
import {VoziloReservationModel} from '../../../../model/voziloReservation.model';
import {VoziloReservationService} from '../../../../service/voziloReservation.service';
import {LokacijaService} from '../../../../service/lokacija.service';
import {CheckAvailabilityModel} from '../../../../model/checkAvailability.model';
import {PricelistRentService} from "../../../../service/pricelistRent.service";
import {AuthService} from "../../../../service/auth.service";
import {OcenaVoziloService} from "../../../../service/ocenaVozilo.service";

@Component({
  selector: 'app-registered-user-servisi-vozila',
  templateUrl: './registered-user-servisi-vozila.component.html',
  styleUrls: ['./registered-user-servisi-vozila.component.css'],
  styles: [`
    .star {
      position: relative;
      display: inline-block;
      font-size: 3rem;
      color: darkgray;
    }
    .full {
      color: dodgerblue;
    }
    .half {
      position: absolute;
      display: inline-block;
      overflow: hidden;
      color: dodgerblue;
    }
  `]
})
export class RegisteredUserServisiVozilaComponent implements OnInit {

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  public numberOfGuests: AbstractControl;
  public tipVozila: AbstractControl;
  public priceRange: AbstractControl;
  public mestoPreuzimanja: AbstractControl;
  public mestoVracanja: AbstractControl;

  d1: any;
  d2: any;
  slobodnaVozila = [];
  nazivRent: string;
  timePeriodConfirmed: boolean;

  public check = false;

  // moje
  // lista filijala tog servisa za combo box
  filijale = [];
  selectedVozilo: any ;
  selectedVoziloID: any ;
  value = '';
  tipoviVozila = [];

  dobarBrojVozila : boolean = false ;

  rating: any ;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private voziloService: VoziloService,
              private userService: UserService,
              private voziloReservationService: VoziloReservationService,
              private lokacijaService: LokacijaService,
              private rentService: RentCarService,
              private authService: AuthService,
              private ocenaService: OcenaVoziloService) {

    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["rentAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate([""]);
        } else if(data.uloga == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["systemAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_HOTELA"){
          this.router.navigate(["hotelAdminPage"]);
        }

      },

      error => {
        this.router.navigate(["prijava"]);
      }
    )

    this.form = this.fb.group({
    'startDate': ['', Validators.compose([Validators.required])],
    'endDate': ['', Validators.compose([Validators.required])],
    'numberOfGuests': ['', Validators.compose([Validators.required, Validators.pattern('[1-9]{1,2}$')])],
    'tipVozila': ['', Validators.compose([Validators.required])],
    'mestoPreuzimanja': ['', Validators.compose([Validators.required])],
    'mestoVracanja': ['', Validators.compose([Validators.required])],
    'priceRange': [''],
  });
    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
    this.numberOfGuests = this.form.controls['numberOfGuests'];
    this.tipVozila = this.form.controls['tipVozila'];
    this.mestoPreuzimanja = this.form.controls['mestoPreuzimanja'];
    this.mestoVracanja = this.form.controls['mestoVracanja'];
    this.priceRange = this.form.controls['priceRange'];

  }

  ngOnInit() {

    this.timePeriodConfirmed = false;

    const idRent = this.route.snapshot.params.idRent;

    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv;
    });

    this.lokacijaService.getFilijaleRentACar(idRent).subscribe(data => {
      this.filijale = data;
    });

    this.rentService.getVoziloTypesInRent(idRent).subscribe(data => {
      this.tipoviVozila = data ;
    })
  }

  goBack() {
    this.router.navigateByUrl('registeredUserPage' );
  }

  confirmClick() {

    this.timePeriodConfirmed = true;
    const idRent = this.route.snapshot.params.idRent;

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const checkAvailabilityRentModel = new CheckAvailabilityRentModel (
      this.d1,
      this.d2,
      this.mestoPreuzimanja.value,
      this.mestoVracanja.value,
      this.numberOfGuests.value,
      this.tipVozila.value,
      this.priceRange.value,
    );

    this.voziloService.checkAvailabilityVozilo(checkAvailabilityRentModel, idRent).subscribe(data => {
      this.slobodnaVozila = data;

      for (let vozilo of this.slobodnaVozila)
      {
        this.ocenaService.getProsecnaOcenaVozila(vozilo.voziloId).subscribe(data => {
          vozilo.ocena = data ;
        })
      }
    });

  }

  logout() {
    this.authService.logOutUser();
  }

  addVozilo(tipVozila: any, id: any) {

    this.check = false ;

    this.selectedVozilo = tipVozila + id ;
    this.selectedVoziloID = id ;
    this.value = tipVozila + '(' + id + ')' + '    ';

    this.checkBrojVozila();

  }

  removeVozilo(tipVozila: any, id: any) {

    this.check = false ;

    this.selectedVoziloID = '';
    this.selectedVoziloID = '';
    this.value = '';

    this.checkBrojVozila();

  }

  checkBrojVozila()
  {
    if (this.value != '') {
      this.dobarBrojVozila = true ;
    } else {
      this.dobarBrojVozila = false ;
    }
  }

  nastaviRezervaciju() {

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const voziloReservationM = new VoziloReservationModel(
      this.d1,
      this.d2,
      this.numberOfGuests.value,
      this.selectedVoziloID,
      this.mestoPreuzimanja.value,
      this.mestoVracanja.value
    );

    const idRent = this.route.snapshot.params.idRent;
    /*
     this.voziloReservationService.voziloReservation(voziloReservationM).subscribe(data => {
       const idRezervacije = data.id ;
       this.router.navigateByUrl('registeredUserPage');
    });
     */


    this.voziloReservationService.createReservationRentNova(voziloReservationM).subscribe(data => {
      // const idRezervacije = data.id ;
      this.router.navigateByUrl('registeredUserPage');
    },

      error => {
      alert('Vozilo koje ste izabrali je u medjuvremenu rezervisano!');
      this.router.navigateByUrl('registeredUserPage');
      })



  }

  vratiProsecnuOcenu(id: any) {
    this.ocenaService.getProsecnaOcenaVozila(id).subscribe(data => {
      this.rating = data;
      if (data == 0 || data == undefined)
      {
        alert('Za ovo vozilo nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ovog vozila je: ' + data);
      }
    })
  }




}

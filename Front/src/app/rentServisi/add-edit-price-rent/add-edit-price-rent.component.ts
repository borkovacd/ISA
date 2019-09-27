import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {PricelistRentService} from "../../service/pricelistRent.service";
import {PricelistRentModel} from "../../model/pricelistRent.model";
import {PriceRentService} from "../../service/priceRent.service";
import {VoziloModel} from "../../model/vozilo.model";
import{PriceRentModel} from "../../model/priceRent.model";
import {PriceModel} from "../../model/price.model";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-add-edit-price-rent',
  templateUrl: './add-edit-price-rent.component.html',
  styleUrls: ['./add-edit-price-rent.component.css']
})
export class AddEditPriceRentComponent implements OnInit {

  public form: FormGroup;
  public tipStavke: AbstractControl;
  public cena: AbstractControl;

  vozilaTypes = [];

  startDate: string;
  endDate: string;
  rentName: string;

  alertMessage: string;
  showAlert: boolean = false;

  public method_name = 'DODAJ';

  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private priceRentService: PriceRentService,
              private pricelistRentService: PricelistRentService,
              private authService: AuthService,
              private userService: UserService) {


    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "OBICAN_KORISNIK"){
          this.router.navigate(["registeredUserPage"]);
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
      'tipStavke': ['', Validators.compose([Validators.required])],
      'cena': ['', Validators.compose([Validators.required, Validators.pattern('^(\\d*\\.)?\\d+$')])],
    })
    this.tipStavke = this.form.controls['tipStavke'];
    this.cena = this.form.controls['cena'];
  }

  ngOnInit() {
    this.showAlert = false;

    const mode = this.route.snapshot.params.mode;
    const idPriceList = this.route.snapshot.params.idPriceList;

    this.pricelistRentService.getPricelistRent(idPriceList).subscribe(data => {
      this.startDate = data.pocetakVazenja;
      this.endDate = data.prestanakVazenja;
      this.rentName = data.rentACar.naziv ;
    })

    this.pricelistRentService.getRoomTypesInRent(idPriceList).subscribe(data => {
      this.vozilaTypes = data;

    })


    if (mode == 'edit') {
      this.method_name = 'IZMENI';

    } else if (mode == 'add') {
      this.method_name = 'DODAJ';

    }
  }

  logout()
  {
    this.authService.logOutUser();
  }

  confirmClick() {
    if (this.method_name === 'DODAJ') {
      this.createPriceRent();
    } else {
      this.editPriceRent();
    }
  }

  private createPriceRent() {

    const idRent = this.route.snapshot.params.idRent;
    const idPriceList = this.route.snapshot.params.idPriceList;

    const price = new PriceRentModel (
      this.tipStavke.value,
      this.cena.value
    );

    this.priceRentService.createPriceRent(price, idPriceList).subscribe(data => {
      if (data == null) {
        this.alertMessage = 'Stavka cenovnika nije uspešno dodata! Pokušajte ponovo!';
      } else {
        this.alertMessage = 'Stavka cenovnika je uspešno dodata!';
      }
      this.showAlert = true;
    })
  }

  exit() {
    const idPriceList = this.route.snapshot.params.idPriceList;
    const idRent = this.route.snapshot.params.idRent;
    this.router.navigateByUrl('rentAdminPage/pricesRent/' + idRent + '/' + idPriceList);
  }

  private editPriceRent() {

  }
  closeAlert() {
    const idRent = this.route.snapshot.params.idRent;
    const idPriceList = this.route.snapshot.params.idPriceList;
    this.router.navigateByUrl('rentAdminPage/pricesRent/' +  idRent + '/' + idPriceList);
  }


}

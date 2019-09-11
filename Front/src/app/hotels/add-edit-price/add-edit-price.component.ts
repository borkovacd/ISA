import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {PricelistService} from '../../service/pricelist.service';
import {PricelistModel} from '../../model/pricelist.model';
import {PriceService} from '../../service/price.service';
import {RoomModel} from '../../model/room.model';
import {PriceModel} from '../../model/price.model';
import {UserService} from '../../service/user.service';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-add-edit-price',
  templateUrl: './add-edit-price.component.html',
  styleUrls: ['./add-edit-price.component.css']
})
export class AddEditPriceComponent implements OnInit {

  administrator : any = null;

  public form: FormGroup;
  public priceType: AbstractControl;
  public tipStavke: AbstractControl;s
  public cena: AbstractControl;

  roomTypes = [];
  additionalServiceTypes = [];

  startDate: string;
  endDate: string;
  hotelName: string;

  alertMessage: string;
  showAlert: boolean = false;

  public method_name = 'DODAJ';
  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private priceService: PriceService,
              private pricelistService: PricelistService,
              private userService: UserService,
              private authService: AuthService) {
    this.form = this.fb.group({
      'priceType': ['', Validators.compose([Validators.required])],
      'tipStavke': ['', Validators.compose([Validators.required])],
      'cena': ['', Validators.compose([Validators.required, Validators.pattern('^(\\d*\\.)?\\d+$')])],
    })
    this.priceType = this.form.controls['priceType'];
    this.tipStavke = this.form.controls['tipStavke'];
    this.cena = this.form.controls['cena'];
  }

  ngOnInit() {

    this.showAlert = false;

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });


    const mode = this.route.snapshot.params.mode;
    const idPriceList = this.route.snapshot.params.idPriceList;

    this.pricelistService.getPricelist(idPriceList).subscribe(data => {
      this.startDate = data.pocetakVazenja;
      this.endDate = data.prestanakVazenja;
      this.hotelName = data.hotel.naziv;
    })

    this.pricelistService.getRoomTypesInHotel(idPriceList).subscribe(data => {
      this.roomTypes = data;
    })

    this.pricelistService.getAdditionalServiceTypesInHotel(idPriceList).subscribe(data => {
      this.additionalServiceTypes = data;
    })

    if (mode == 'edit') {
      this.method_name = 'IZMENI';

    } else if (mode == 'add') {
      this.method_name = 'DODAJ';

    }
  }

  confirmClick() {
    if (this.method_name === 'DODAJ') {
      this.createPrice();
    } else {
      this.editPrice();
    }
  }
  private createPrice() {

    const idHotela = this.route.snapshot.params.idHotela;
    const idPriceList = this.route.snapshot.params.idPriceList;

    const price = new PriceModel (
      this.tipStavke.value,
      this.cena.value
    );

    this.priceService.createPrice(price, idPriceList).subscribe(data => {
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
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/prices/' + idHotela + '/' + idPriceList);
  }

  private editPrice() {

  }
  closeAlert() {
    const idHotela = this.route.snapshot.params.idHotela;
    const idPriceList = this.route.snapshot.params.idPriceList;
    this.router.navigateByUrl('hotelAdminPage/prices/' +  idHotela + '/' + idPriceList);
  }

  logout()
  {
    this.authService.logOutUser();
  }


}



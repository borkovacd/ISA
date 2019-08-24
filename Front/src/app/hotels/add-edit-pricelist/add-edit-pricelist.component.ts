import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {PricelistService} from '../../service/pricelist.service';
import {PricelistModel} from '../../model/pricelist.model';

@Component({
  selector: 'app-add-edit-pricelist',
  templateUrl: './add-edit-pricelist.component.html',
  styleUrls: ['./add-edit-pricelist.component.css']
})
export class AddEditPricelistComponent implements OnInit {

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  d1: any;
  d2: any;
  naslovStranice: string;


  public method_name = 'DODAJ';
  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private pricelistService: PricelistService,) {
    this.form = this.fb.group({
      'startDate': ['', Validators.compose([Validators.required])],
      'endDate': ['', Validators.compose([Validators.required])],
    })
    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
  }

  ngOnInit() {

    const mode = this.route.snapshot.params.mode;
    const idHotela = this.route.snapshot.params.idHotela;

    if (mode == 'edit') {
      this.method_name = 'IZMENI';
      this.naslovStranice = 'Izmena cenovnika';
      this.pricelistService.getPricelist(idHotela).subscribe(data => {
        this.form.controls['startDate'].setValue(data.pocetakVazenja);
        this.form.controls['endDate'].setValue(data.prestanakVazenja);
      })
    } else if (mode == 'add') {
      this.method_name = 'DODAJ';
      this.naslovStranice = 'Dodavanje cenovnika';
    }
  }

  confirmClick() {
    if (this.method_name === 'DODAJ') {
      this.createPricelist();
    } else {
      this.editPricelist();
    }
  }
  /*
  createRoom(){
    const idHotela = this.route.snapshot.params.idHotela;

    const room = new RoomModel(
      this.capacity.value,
      this.floor.value,
      this.hasBalcony.value,
      this.roomType.value
    );
    this.roomService.createRoom(room, idHotela).subscribe(data => {
      this.router.navigateByUrl('hotelAdminPage/rooms/' +  idHotela);
    })
  }
  editRoom() {
    const idHotela = this.route.snapshot.params.idHotela;
    const idRoom = this.route.snapshot.params.idPriceList;

    const room = new RoomModel(
      this.capacity.value,
      this.floor.value,
      this.hasBalcony.value,
      this.roomType.value
    );
    this.roomService.editRoom(room, idRoom).subscribe(data => {
      this.router.navigateByUrl('hotelAdminPage/rooms/' + idHotela);
    })
  }*/

  exit() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/pricelists/' +  idHotela);
  }

  private createPricelist() {
    const idHotela = this.route.snapshot.params.idHotela;

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const pricelist = new PricelistModel(
      this.d1,
      this.d2
    );
    this.pricelistService.createPricelist(pricelist, idHotela).subscribe(data => {
      this.router.navigateByUrl('hotelAdminPage/pricelists/' +  idHotela);
    });

  }

  private editPricelist() {

  }
}


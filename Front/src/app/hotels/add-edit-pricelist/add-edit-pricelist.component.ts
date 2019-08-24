import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../service/room.service';
import {RoomModel} from '../../model/room.model';
import {PricelistService} from '../../service/pricelist.service';

@Component({
  selector: 'app-add-edit-pricelist',
  templateUrl: './add-edit-pricelist.component.html',
  styleUrls: ['./add-edit-pricelist.component.css']
})
export class AddEditPricelistComponent implements OnInit {

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
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
    const idPriceList = this.route.snapshot.params.idPriceList;
    const idHotela = this.route.snapshot.params.idHotela;

    if (mode == 'edit') {
      this.method_name = 'IZMENI';
      this.naslovStranice = 'Izmena cenovnika';
      this.pricelistService.getPricelist(idPriceList).subscribe(data => {
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
    const idRoom = this.route.snapshot.params.idRoom;

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

  }

  private editPricelist() {

  }
}


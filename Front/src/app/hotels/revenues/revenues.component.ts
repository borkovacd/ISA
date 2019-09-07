import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../service/hotel.service';

@Component({
  selector: 'app-revenues',
  templateUrl: './revenues.component.html',
  styleUrls: ['./revenues.component.css']
})
export class RevenuesComponent implements OnInit {

  public form: FormGroup;
  d1: AbstractControl;
  d2: AbstractControl;
  nazivHotela: string;
  revenueValue: number;
  showValue: boolean;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              public fb: FormBuilder,
              private hotelService: HotelService) {
    this.form = this.fb.group({
      'd1': ['', Validators.required],
      'd2': ['', Validators.required],
    });
    this.d1 = this.form.controls['d1'];
    this.d2 = this.form.controls['d2'];
  }

  ngOnInit() {
    this.showValue = false;

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })
  }

  confirm() {

    if(this.d1.value >= this.d2.value) {
      alert("Neispravan unos početnog i krajnjeg datuma. Pokušajte ponovo!");
    } else {
      const idHotela = this.route.snapshot.params.idHotela;
      this.hotelService.getRevenues(idHotela, this.d1.value, this.d2.value).subscribe(data => {
        this.revenueValue = data;
        this.showValue = true;
      });
    }
  }

  goBack() {
    this.router.navigateByUrl('hotelAdminPage' );

  }

}

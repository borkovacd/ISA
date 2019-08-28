import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../service/hotel.service';
import {AdditionalServiceService} from '../../service/additionalService.service';

@Component({
  selector: 'app-additional-services',
  templateUrl: './additional-services.component.html',
  styleUrls: ['./additional-services.component.css']
})
export class AdditionalServicesComponent implements OnInit {

  additionalServices = [];
  nazivHotela: string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private additionalServiceService: AdditionalServiceService) {}

  ngOnInit() {

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

    this.additionalServiceService.getAllAdditionalServices(idHotela).subscribe(data => {
      this.additionalServices = data;
    })
  }

  goBack() {
    this.router.navigateByUrl('hotelAdminPage' );

  }

  addAdditionalService() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/additionalService/' +  idHotela);

  }

  deleteAdditionalService(id: any) {
    const idHotela = this.route.snapshot.params.idHotela;
    this.additionalServiceService.deleteAdditionalService(id).subscribe(data => {
      location.reload();
    })

  }
}


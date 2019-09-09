import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../service/room.service';
import {HotelService} from '../../service/hotel.service';
import {PricelistService} from '../../service/pricelist.service';

@Component({
  selector: 'app-pricelists',
  templateUrl: './pricelists.component.html',
  styleUrls: ['./pricelists.component.css']
})
export class PricelistsComponent implements OnInit {

  pricelists = []
  nazivHotela: string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private pricelistService: PricelistService) {}
  ngOnInit() {

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

    this.pricelistService.getAllPricelists(idHotela).subscribe(data => {
      this.pricelists = data;
    })

  }

  /*addRoom() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/room/' +  idHotela  + '/add/');
  }

  editRoom(id: any){
    const idHotela = this.route.snapshot.params.idHotela;
    this.roomService.checkIfReservedRoom(id).subscribe(data => {
      if (data == false) {
        this.router.navigateByUrl('hotelAdminPage/room/' + idHotela + '/edit/' + id );
      } else {
        alert('Soba je rezervisana!');
      }
    })
  }

  deleteRoom(id: any) {
    const idHotela = this.route.snapshot.params.idHotela;
    this.roomService.checkIfReservedRoom(id).subscribe(data => {
      if (data == false) {
        this.roomService.deleteRoom(idHotela , id).subscribe(data => {
          alert('Soba je uspesno obrisana');
          this.router.navigateByUrl('hotelAdminPage/rooms/' + idHotela);
        })
        location.reload();
      } else {
        alert('Soba je rezervisana!');
      }
    })
  }*/

  goBack(){
    this.router.navigateByUrl('hotelAdminPage' );

  }

  addPricelist() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/pricelist/' +  idHotela  + '/add/');
  }


  showPricelist(idPriceList: any) {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/prices/' + idHotela + '/' + idPriceList);
  }
}

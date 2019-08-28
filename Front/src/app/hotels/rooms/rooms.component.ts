import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../service/room.service';
import {HotelService} from '../../service/hotel.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {

  rooms = []
  idRoom: any;
  nazivHotela: string;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private hotelService: HotelService) {}
  ngOnInit() {
    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })
    this.roomService.getAllRooms(idHotela).subscribe(data => {
      this.rooms = data;
    })

  }

  addRoom() {
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
        location.reload();
      } else {
        alert('Soba je rezervisana!');
      }
    })
  }

  goBack(){
    this.router.navigateByUrl('hotelAdminPage' );

  }

}

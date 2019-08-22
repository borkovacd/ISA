import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../service/room.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {

  rooms = []
  idRoom: any;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private roomService: RoomService) {}
  ngOnInit() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.roomService.getAllRooms(idHotela).subscribe(data =>{
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
        this.roomService.deleteRoom(idHotela , id).subscribe(data => {
          alert('Soba je uspesno obrisana');
          this.router.navigateByUrl('hotelAdminPage/room/' + idHotela);
        })
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

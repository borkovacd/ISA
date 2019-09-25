import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../service/room.service';
import {HotelService} from '../../service/hotel.service';
import {OcenaSobaService} from "../../service/ocenaSoba.service";
import {UserService} from '../../service/user.service';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css'],
  styles: [`
    .star {
      position: relative;
      display: inline-block;
      font-size: 3rem;
      color: #d3d3d3;
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
export class RoomsComponent implements OnInit {

  administrator : any = null;

  rooms = []
  idRoom: any;
  nazivHotela: string;

  rating: any ;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private hotelService: HotelService,
              private ocenaService: OcenaSobaService,
              private  userService: UserService,
              private authService: AuthService) {}

  ngOnInit() {
    const idHotela = this.route.snapshot.params.idHotela;

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })
    this.roomService.getAllRooms(idHotela).subscribe(data => {
      this.rooms = data;

      for (let room of this.rooms)
      {
        this.ocenaService.getProsecnaOcenaSoba(room.id).subscribe(data => {
          room.ocena = data ;
        })
      }
    })

  }

  addRoom() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/room/' +  idHotela  + '/add/');
  }

  staviNaPopust(id: any) {
    const idHotela = this.route.snapshot.params.idHotela;
    this.roomService.checkIfReservedRoom(id).subscribe(data => {
      if (data == false) {
        this.roomService.staviNaPopust(id).subscribe(data => {
          location.reload();
        })
      } else {
        alert('Soba je rezervisana!');
      }
    })
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
        this.roomService.deleteRoom(idHotela, id).subscribe(data => {
          this.router.navigateByUrl('hotelAdminPage/rooms/' + idHotela);
        })
        location.reload();
      } else {
        alert('Nije moguće obrisati sobu koja je rezervisna!');

      }
    });
  }

  goBack(){
    this.router.navigateByUrl('hotelAdminPage' );

  }

  vratiProsecnuOcenu(id: any)
  {
    this.ocenaService.getProsecnaOcenaSoba(id).subscribe(data => {
      this.rating = data;
      if (data == 0 || data == undefined)
      {
        alert('Za ovu sobu nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ocena ove sobe je: ' + data);
      }
    })
  }

  logout()
  {
    this.authService.logOutUser();
  }
}

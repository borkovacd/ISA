import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../service/room.service';
import {RoomModel} from '../../model/room.model';
import {UserService} from '../../service/user.service';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-add-edit-room',
  templateUrl: './add-edit-room.component.html',
  styleUrls: ['./add-edit-room.component.css']
})
export class AddEditRoomComponent implements OnInit {

  administrator : any = null;

  public form: FormGroup;
  public capacity: AbstractControl;
  public floor: AbstractControl;
  public hasBalcony: AbstractControl;
  public roomType: AbstractControl;
  naslovStranice: string;


  public method_name = 'DODAJ';
  constructor(protected  router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private roomService: RoomService,
              private  userService: UserService,
              private authService: AuthService) {
    this.form = this.fb.group({
      'capacity': ['', Validators.compose([Validators.required, Validators.pattern('[1-9]{1,2}$')])],
      'floor': ['', Validators.compose([Validators.required, Validators.pattern('^-?[0-9]{1,3}$')])],
      'hasBalcony': [''],
      'roomType': ['', Validators.compose([Validators.required])],
    })
    this.capacity = this.form.controls['capacity'];
    this.floor = this.form.controls['floor'];
    this.hasBalcony = this.form.controls['hasBalcony'];
    this.roomType = this.form.controls['roomType'];
  }

  ngOnInit() {

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });


    const mode = this.route.snapshot.params.mode;
    const idRoom = this.route.snapshot.params.idRoom;
    const idHotela = this.route.snapshot.params.idHotela;

    if (mode == 'edit') {
      this.method_name = 'IZMENI';
      this.naslovStranice = 'Izmena sobe';
      this.roomService.getRoom(idRoom).subscribe(data => {
        this.form.controls['capacity'].setValue(data.kapacitet);
        this.form.controls['floor'].setValue(data.sprat);
        this.form.controls['hasBalcony'].setValue(data.imaBalkon);
        this.form.controls['roomType'].setValue(data.tipSobe);
      })
    } else if (mode == 'add') {
      this.method_name = 'DODAJ';
      this.naslovStranice = 'Dodavanje sobe';
    }
  }

  confirmClick() {
    if (this.method_name === 'DODAJ') {
      this.createRoom();
    } else {
      this.editRoom();
    }
  }

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
  }

  exit() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/rooms/' +  idHotela);
  }

  logout()
  {
    this.authService.logOutUser();
  }
}


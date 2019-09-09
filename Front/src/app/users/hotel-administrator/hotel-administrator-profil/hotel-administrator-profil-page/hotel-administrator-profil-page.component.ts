import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../../service/user.service';
import {Router} from '@angular/router';
import {AuthService} from '../../../../service/auth.service';

@Component({
  selector: 'app-hotel-administrator-profil-page',
  templateUrl: './hotel-administrator-profil-page.component.html',
  styleUrls: ['./hotel-administrator-profil-page.component.css']
})
export class HotelAdministratorProfilPageComponent implements OnInit {

  administrator: any = null;

  constructor(private  userService: UserService,
              protected router: Router,
              private authService: AuthService) { }

  public ngOnInit() {

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });
  }


}

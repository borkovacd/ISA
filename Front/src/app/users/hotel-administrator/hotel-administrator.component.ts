import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";



@Component({
  selector : 'hotel-administrator',
  templateUrl : './hotel-administrator.component.html',
  styleUrls   : ['./hotel-administrator.component.scss']
})
export class HotelAdministratorComponent implements  OnInit {

  constructor(private  userService: UserService, protected router: Router, private authService: AuthService) { }

  public ngOnInit() {

  }

  logout()
  {
    this.authService.logOutUser();
  }
}

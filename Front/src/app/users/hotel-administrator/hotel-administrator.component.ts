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

  administrator : any = null;

  constructor(private  userService: UserService,
              protected router: Router,
              private authService: AuthService) { }

  public ngOnInit() {

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });
  }
  logout()
  {
    this.authService.logOutUser();
  }
}

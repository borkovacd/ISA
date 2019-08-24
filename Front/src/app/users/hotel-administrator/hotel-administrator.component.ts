import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";



@Component({
  selector : 'hotel-administrator',
  templateUrl : './hotel-administrator.component.html',
  styleUrls   : ['./hotel-administrator.component.scss']
})
export class HotelAdministratorComponent implements  OnInit {

  constructor(private  userService: UserService, protected router: Router) { }

  public ngOnInit() {

  }

  logout()
  {
    this.userService.logOut().subscribe(
      data => {
        this.router.navigate(['/welcomepage']);
      }
    )
  }
}

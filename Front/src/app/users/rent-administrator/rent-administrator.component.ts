import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-rent-administrator',
  templateUrl: './rent-administrator.component.html',
  styleUrls: ['./rent-administrator.component.css']
})
export class RentAdministratorComponent implements OnInit {

  constructor(private userService : UserService, private router: Router) { }

  ngOnInit() {
    //alert(localStorage.getItem('loggedUser'));
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

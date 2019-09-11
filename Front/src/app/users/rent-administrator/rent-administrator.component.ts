import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-rent-administrator',
  templateUrl: './rent-administrator.component.html',
  styleUrls: ['./rent-administrator.component.css']
})
export class RentAdministratorComponent implements OnInit {

  administrator : any = null;

  constructor(private userService : UserService, private router: Router, private authService:AuthService) { }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });
  }

  logout()
  {
    this.authService.logOutUser();
  }

}

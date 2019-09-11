import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../../service/auth.service";

@Component({
  selector: 'app-rent-administrator-profil-page',
  templateUrl: './rent-administrator-profil-page.component.html',
  styleUrls: ['./rent-administrator-profil-page.component.css']
})
export class RentAdministratorProfilPageComponent implements OnInit {

  administrator: any = null;

  constructor(private  userService: UserService,
              protected router: Router,
              private authService: AuthService) { }

  ngOnInit() {

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });
  }

}

import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-registered-user',
  templateUrl: './registered-user.component.html',
  styleUrls: ['./registered-user.component.css']
})
export class RegisteredUserComponent implements OnInit {

  constructor(private userService : UserService,
              private router: Router,
              private authService: AuthService) { }

  ngOnInit() {
  }

  logout()
  {
    this.authService.logOutUser();
  }

  brzaRezervacija() {
    this.router.navigateByUrl('/brzaRezervacija/hoteli/1')
  }
}

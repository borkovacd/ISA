import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector : 'system-administrator',
  templateUrl : './system-administrator.component.html',
  styleUrls   : ['./system-administrator.component.scss']
})
export class SystemAdministratorComponent implements  OnInit {

  constructor(private userService : UserService, private router: Router, private authService: AuthService) { }

  public ngOnInit()
  {

  }

  logout()
  {
    this.authService.logOutUser();
  }

}

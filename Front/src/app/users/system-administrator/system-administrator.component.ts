import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector : 'system-administrator',
  templateUrl : './system-administrator.component.html',
  styleUrls   : ['./system-administrator.component.scss']
})
export class SystemAdministratorComponent implements  OnInit{

  constructor(private userService : UserService, private router: Router) { }

  public ngOnInit()
  {

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

import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../../service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registered-user-hoteli-dodatne-usluge',
  templateUrl: './registered-user-hoteli-dodatne-usluge.component.html',
  styleUrls: ['./registered-user-hoteli-dodatne-usluge.component.css']
})
export class RegisteredUserHoteliDodatneUslugeComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {
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

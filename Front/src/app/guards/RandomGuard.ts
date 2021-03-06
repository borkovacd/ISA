import { Injectable } from '@angular/core';
import { CanActivate, CanLoad, Router } from '@angular/router';
import { AuthService} from "../service/auth.service";


@Injectable({
  providedIn: 'root'
})
export class RandomGuard  implements  CanActivate , CanLoad {
  constructor(private authService: AuthService, private router: Router) { }

  canActivate() {
    return this.canLoad();
  }

  canLoad() {

    if (!this.authService.isUserLogged()) {
      this.router.navigate(['/prijava']);
    }
    return this.authService.isUserLogged();
  }

}

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../service/hotel.service';
import {AdditionalServiceService} from '../../service/additionalService.service';
import {UserService} from '../../service/user.service';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-additional-services',
  templateUrl: './additional-services.component.html',
  styleUrls: ['./additional-services.component.css']
})
export class AdditionalServicesComponent implements OnInit {

  additionalServices = [];
  nazivHotela: string;
  administrator : any = null;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private additionalServiceService: AdditionalServiceService,
              private  userService: UserService,
              private authService: AuthService) {
    this.userService.vratiTrenutnogKorisnikaAutor().subscribe(
      data => {

        if(data.uloga == "ADMINISTRATOR_RENT_A_CAR"){
          this.router.navigate(["rentAdminPage"]);
        } else if(data.uloga == "ADMINISTRATOR_AVIOKOMPANIJE"){
          this.router.navigate([""]);
        } else if(data.uloga == "ADMINISTRATOR_SISTEMA"){
          this.router.navigate(["systemAdminPage"]);
        } else if(data.uloga == "OBICAN_KORISNIK"){
          this.router.navigate(["registeredUserPage"]);
        }

      },

      error => {
        this.router.navigate(["prijava"]);
      }
    )
  }

  ngOnInit() {

    this.userService.getCurrentUser().subscribe(data => {
      this.administrator = data;
    });

    const idHotela = this.route.snapshot.params.idHotela;

    this.hotelService.getHotel(idHotela).subscribe(data => {
      this.nazivHotela = data.naziv;
    })

    this.additionalServiceService.getAllAdditionalServices(idHotela).subscribe(data => {
      this.additionalServices = data;
    })
  }

  goBack() {
    this.router.navigateByUrl('hotelAdminPage' );

  }

  addAdditionalService() {
    const idHotela = this.route.snapshot.params.idHotela;
    this.router.navigateByUrl('hotelAdminPage/additionalService/' +  idHotela);

  }

  deleteAdditionalService(id: any) {
    const idHotela = this.route.snapshot.params.idHotela;
    this.additionalServiceService.checkIfReservedService(id).subscribe(data => {
      if (data == false) {
        this.additionalServiceService.deleteAdditionalService(id).subscribe(data => {
          location.reload();
        });
      } else {
        alert('Nije moguće obrisati dodatnu uslugu koja je rezervisna!');
      }
    })
  }

  logout()
  {
    this.authService.logOutUser();
  }


}


import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VoziloService} from '../../service/vozilo.service';
import {RentCarService} from '../../service/rentcar.service';
import {OcenaVoziloService} from "../../service/ocenaVozilo.service";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-vozila',
  templateUrl: './vozila.component.html',
  styleUrls: ['./vozila.component.css'],
  styles: [`
    .star {
      position: relative;
      display: inline-block;
      font-size: 3rem;
      color: darkgray;
    }
    .full {
      color: dodgerblue;
    }
    .half {
      position: absolute;
      display: inline-block;
      overflow: hidden;
      color: dodgerblue;
    }
  `]
})

export class VozilaComponent implements OnInit {

  vozila = [];
  idVozilo: any;
  nazivRent: string;

  rating : any ;

  constructor(protected  router: Router,
              private route: ActivatedRoute,
              private voziloService: VoziloService,
              private rentService: RentCarService,
              private ocenaService: OcenaVoziloService,
              private authService: AuthService) { }

  ngOnInit() {

    const idRent = this.route.snapshot.params.idRent ;

    this.rentService.getRent(idRent).subscribe(data => {
      this.nazivRent = data.naziv ;
    });

    this.voziloService.getVozilaRentACar(idRent).subscribe(data => {
      this.vozila = data;

      for (let vozilo of this.vozila)
      {
        this.ocenaService.getProsecnaOcenaVozila(vozilo.voziloId).subscribe(data => {
          vozilo.ocena = data ;
        })
      }
    });

  }

  logout()
  {
    this.authService.logOutUser();
  }

  vratiProsecnuOcenu(id: any)
  {
    this.ocenaService.getProsecnaOcenaVozila(id).subscribe(data => {
      this.rating = data;

      if (data == 0 || data == undefined)
      {
        alert('Za ovo vozilo nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ocena ovog vozila je: ' + this.rating);
      }
    })

  }

  addVozilo() {
    const idRent = this.route.snapshot.params.idRent ;
    this.router.navigateByUrl('rentAdminPage/vozilo/' + idRent + '/add/' );
  }

  editVozilo(id: any) {
    const idRent = this.route.snapshot.params.idRent ;
    this.voziloService.checkIfReservedVozilo(id).subscribe(data => {
      if (data == false)
      {
        this.router.navigateByUrl('rentAdminPage/vozilo/' + idRent + '/edit/' + id);
      }
      else
      {
        alert('Vozilo je rezervisano, pa se ne moze vrsiti izmena!')
      }
    })
  }

  deleteVozilo(id: any) {
    const idRent = this.route.snapshot.params.idRent ;

    this.voziloService.checkIfReservedVozilo(id).subscribe(data => {
      if (data == false)
      {
        this.voziloService.obrisiVozilo(idRent, id).subscribe(data => {
          this.router.navigateByUrl('rentAdminPage');
        }) ;
      } else
      {
        alert('Vozilo je rezervisano, pa se ne moze vrsiti brisanje!')
      }
    })

  }

  staviNaPopust(id: any) {
    const idRent = this.route.snapshot.params.idRent;
    this.voziloService.checkIfReservedVoziloPopust(id).subscribe(data => {
      if (data == false) {
        this.voziloService.staviVoziloNaPopust(id).subscribe(data => {
          location.reload();
        })
      } else {
        alert('Vozilo je rezervisano!');
      }
    })
  }

  skiniSaPopusta(id: any) {
    const idRent = this.route.snapshot.params.idRent;
    this.voziloService.checkIfReservedVoziloPopust(id).subscribe(data => {
      if (data == false) {
        this.voziloService.skiniVoziloSaPopusta(id).subscribe(data => {
          location.reload();
        })
      } else {
        alert('Vozilo je rezervisano!');
      }
    })
  }

  goBack() {
    this.router.navigateByUrl('rentAdminPage' );
  }






}

import { Component, OnInit, ViewChild, ElementRef, NgZone} from '@angular/core';
import {HotelService} from '../../../service/hotel.service';
import {Router} from '@angular/router';
import { MapsAPILoader, MouseEvent } from '@agm/core';
import {Observable} from 'rxjs';
import {OcenaHotelService} from "../../../service/ocenaHotel.service";

@Component({
  selector : 'hotel-administrator-hoteli',
  templateUrl : './hotel-administrator-hoteli.component.html',
  styleUrls   : ['./hotel-administrator-hoteli.component.scss']
})
export class HotelAdministratorHoteliComponent implements  OnInit {

  latitude: number;
  longitude: number;
  zoom: number;
  address: string;
  private geoCoder;

  hotels = [];

  showMap: boolean;
  showLocation: boolean;
  hideData: boolean;
  tempAdresa: any;

  rating : any ;

  @ViewChild('search', {static: false})
  public searchElementRef: ElementRef;



  constructor(protected router: Router,
              private hotelService: HotelService,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone,
              private ocenaService: OcenaHotelService) {
  }

  public ngOnInit() {

    this.showMap = false;
    this.showLocation = false;
    this.hideData = false;

    this.hotelService.getHotelsByAdministrator().subscribe(data => {
      this.hotels = data;

      for (let hotel of this.hotels)
      {
        this.ocenaService.getProsecnaOcenaHotel(hotel.id).subscribe(data => {
          hotel.ocena = data ;
        })
      }
    });

  }

  // Get Current Location Coordinates
  /*private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 8;
        this.getAddress(this.latitude, this.longitude);
      });
    }
  }

  getAddress(latitude, longitude) {
    this.geoCoder.geocode({'location': {lat: latitude, lng: longitude}}, (results, status) => {
      console.log(results);
      console.log(status);
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 12;
          this.address = results[0].formatted_address;
        } else {
          window.alert('No results found');
        }
      } else {
        window.alert('Geocoder failed due to: ' + status);
      }

    });
  }*/


  showOnMap(adresa: any) {

    this.hideData = true;
    this.showLocation = true;
    this.tempAdresa = adresa;
    this.showMap = true; //proba

    //load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      //this.setCurrentLocation();
      this.geoCoder = new google.maps.Geocoder;

      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //this.showMap = true; originalno

          //set latitude, longitude and zoom
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.zoom = 15;
        });
      });
    });


  }


  closeMap() {
    this.hideData = false;
    this.showMap = false;
    this.showLocation = false;
  }

  izmeniHotel(id: any) {
    this.hotelService.checkIfReservedHotel(id).subscribe(data => {
      if (data == false) {
        this.router.navigateByUrl('hotelAdminPage/editHotel/' + id);
      } else {
        alert('Nije moguće izmeniti hotel u kojem postoje rezervisane sobe!');
      }
    })
  }

  pregledSoba(idHotela: any) {
    this.router.navigateByUrl('hotelAdminPage/rooms/' + idHotela);
  }

  pregledCenovnika(idHotela: any){
    this.router.navigateByUrl('hotelAdminPage/pricelists/' + idHotela);
  }

  pregledDodatnihUsluga(idHotela: any) {
    this.router.navigateByUrl('hotelAdminPage/additionalServices/' + idHotela);
  }

  pregledGrafika(idHotela: any) {
    this.router.navigateByUrl('hotelAdminPage/attendanceGraphs/' + idHotela);
  }

  pregledPrihoda(idHotela: any) {
    this.router.navigateByUrl('hotelAdminPage/revenues/' + idHotela);
  }

  vratiProsecnuOcenu(id: any)
  {
    this.ocenaService.getProsecnaOcenaHotel(id).subscribe(data => {
      this.rating = data;
      if (data == 0 || data == undefined)
      {
        alert('Za ovaj hotel nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ocena ovog hotela je: ' + data);
      }
    })
  }
}


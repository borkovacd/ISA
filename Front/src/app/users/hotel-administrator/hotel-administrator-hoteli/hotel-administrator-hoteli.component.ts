import { Component, OnInit, ViewChild, ElementRef, NgZone} from '@angular/core';
import {HotelService} from '../../../service/hotel.service';
import {Router} from '@angular/router';
import { MapsAPILoader, MouseEvent } from '@agm/core';
import {Observable} from 'rxjs';

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


  @ViewChild('search', {static: true})
  public searchElementRef: ElementRef;

  constructor(protected router: Router,
              private hotelService: HotelService,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone) {
  }

  public ngOnInit() {

    this.hotelService.getHotelsByAdministrator().subscribe(data => {
      this.hotels = data;
    });

    //load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      this.setCurrentLocation();
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

          //set latitude, longitude and zoom
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.zoom = 12;
        });
      });
    });
  }

  // Get Current Location Coordinates
  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 8;
        this.getAddress(this.latitude, this.longitude);
      });
    }
  }


  markerDragEnd($event: MouseEvent) {
    console.log($event);
    this.latitude = $event.coords.lat;
    this.longitude = $event.coords.lng;
    this.getAddress(this.latitude, this.longitude);
  }

  /* pogledaj ovo */
  private getLocation(address: string): Observable<any> {
    console.log('Getting address: ', address);
    const geocoder = new google.maps.Geocoder();
    return Observable.create(observer => {
      geocoder.geocode({
        address: address
      }, (results, status) => {
        if (status == google.maps.GeocoderStatus.OK) {
          observer.next(results[0].geometry.location);
          observer.complete();
          this.latitude = results[0].geometry.location.lat();
          this.longitude = results[0].geometry.location.lng();
          this.zoom = 12;
        } else {
          console.log('Error: ', results, ' & Status: ', status);
          observer.error();
        }
      });
    });
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
  }


  showOnMap(adresa: any) {

  }


}


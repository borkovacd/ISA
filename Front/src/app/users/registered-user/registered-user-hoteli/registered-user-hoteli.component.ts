import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {HotelService} from '../../../service/hotel.service';
import {MapsAPILoader} from '@agm/core';

@Component({
  selector: 'app-registered-user-hoteli',
  templateUrl: './registered-user-hoteli.component.html',
  styleUrls: ['./registered-user-hoteli.component.css']
})
export class RegisteredUserHoteliComponent implements OnInit {

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

  @ViewChild('search', {static: false})
  public searchElementRef: ElementRef;

  constructor(protected router: Router,
              private hotelService: HotelService,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone) {
  }

  public ngOnInit() {

    this.showMap = false;
    this.showLocation = false;
    this.hideData = false;

    this.hotelService.getAllHotels().subscribe(data => {
      this.hotels = data;
    });

  }

  showOnMap(adresa: any) {

    this.hideData = true;
    this.showLocation = true;
    this.tempAdresa = adresa;

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

          this.showMap = true;

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

  izaberiHotel(id: any) {
    
  }
}



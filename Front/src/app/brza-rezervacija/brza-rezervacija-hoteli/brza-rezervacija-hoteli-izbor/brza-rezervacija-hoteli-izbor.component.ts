import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';
import {FormBuilder} from '@angular/forms';
import {MapsAPILoader} from '@agm/core';
import {HotelService} from '../../../service/hotel.service';

@Component({
  selector: 'app-brza-rezervacija-hoteli-izbor',
  templateUrl: './brza-rezervacija-hoteli-izbor.component.html',
  styleUrls: ['./brza-rezervacija-hoteli-izbor.component.css']
})
export class BrzaRezervacijaHoteliIzborComponent implements OnInit {

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

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService,
              public fb: FormBuilder,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone,
              private hotelService: HotelService) { }

  ngOnInit() {

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
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/sobe/' + + idRezervacijeLeta + '/' + id);

  }

  goBack(){
    const idRezervacijeLeta = this.route.snapshot.params.idRezervacijeLeta;
    this.router.navigateByUrl('brzaRezervacija/hoteli/' + idRezervacijeLeta);
  }

  logout()
  {
    this.authService.logOutUser();
  }

}

import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelService} from '../../../service/hotel.service';
import {MapsAPILoader} from '@agm/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TimePeriodModel} from '../../../model/timePeriod.model';
import {SearchHotelsModel} from '../../../model/searchHotels.model';
import {OcenaHotelService} from "../../../service/ocenaHotel.service";

@Component({
  selector: 'app-registered-user-hoteli',
  templateUrl: './registered-user-hoteli.component.html',
  styleUrls: ['./registered-user-hoteli.component.css']
})
export class RegisteredUserHoteliComponent implements OnInit {

  public tipSort: AbstractControl;

  latitude: number;
  longitude: number;
  zoom: number;
  address: string;
  private geoCoder;

  hotels : any = [];

  //showMap: boolean;
  showLocation: boolean;
  hideData: boolean;
  tempAdresa: any;
  pretraga: boolean;

  public form: FormGroup;
  public startDate: AbstractControl;
  public endDate: AbstractControl;
  public hotelName: AbstractControl;
  public hotelLocation: AbstractControl;
  d1: any;
  d2: any;

  rating : any ;

  @ViewChild('search', {static: false})
  public searchElementRef: ElementRef;
  nemaViseSortiranja: boolean;


  constructor(protected router: Router,
              public fb: FormBuilder,
              private route: ActivatedRoute,
              private hotelService: HotelService,
              private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone,
              private ocenaService: OcenaHotelService) {
    this.form = this.fb.group({
      'startDate': ['', Validators.compose([Validators.required])],
      'endDate': ['', Validators.compose([Validators.required])],
      'hotelName': [''],
      'hotelLocation': [''],
      'tipSort': [''],
    })
    this.startDate = this.form.controls['startDate'];
    this.endDate = this.form.controls['endDate'];
    this.hotelName = this.form.controls['hotelName'];
    this.hotelLocation = this.form.controls['hotelLocation'];
    this.tipSort = this.form.controls['tipSort'];
  }

  public ngOnInit() {

    //this.showMap = false;
    this.pretraga = false;
    this.showLocation = false;
    this.hideData = false;
    this.nemaViseSortiranja = false;

    this.hotelService.getAllHotels().subscribe(data => {
      this.hotels = data;

      for (let hotel of this.hotels)
      {
        this.ocenaService.getProsecnaOcenaHotel(hotel.id).subscribe(data => {
          hotel.ocena = data ;
        })
      }
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

          //this.showMap = true;

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
    //this.showMap = false;
    this.showLocation = false;
  }

  izaberiHotel(id: any) {
    this.router.navigateByUrl('registeredUserPage/rooms/' + id);

  }

  pretraziHotele() {
    this.pretraga = true;
  }

  confirmClick() {
    if (this.startDate.value > this.endDate.value ) {
      alert('Uneti datum nije validan. Pokušajte ponovo.');
      this.startDate.reset();
      this.endDate.reset();
      return;
    }

    this.d1 = this.startDate.value;
    this.d2 = this.endDate.value;

    const searchHotels = new SearchHotelsModel (
      this.hotelName.value,
      this.hotelLocation.value,
      this.d1,
      this.d2
    );

    this.hotelService.searchHotels(searchHotels).subscribe(data => {
      this.hotels = data;
      this.pretraga = false;
      this.nemaViseSortiranja = true;
    });
  }

  goBack(){
    location.reload();
  }

  vratiProsecnuOcenu(id: any) {
    this.ocenaService.getProsecnaOcenaHotel(id).subscribe(data => {
      this.rating = data;
      if (data == 0 || data == undefined)
      {
        alert('Za ovaj hotel nije moguce prikazati prosecnu ocenu!')
      }
      else
      {
        alert('Prosecna ovog hotela je: ' + data);
      }
    })
  }

  // sortiranje
  sortirajHotele(){
    let sort : string = this.tipSort.value;
    this.hotelService.sortHotele(sort).subscribe(
      data => {
        this.hotels = data;
      },
      error => {
        console.log(error);
      }
    )
  }
}



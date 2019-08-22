import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HotelModel} from '../../../model/hotel.model';
import {HotelService} from '../../../service/hotel.service';
import {UserService} from '../../../service/user.service';

@Component({
  selector : 'system-administrator-hoteli',
  templateUrl : './system-administrator-hoteli.component.html',
  styleUrls   : ['./system-administrator-hoteli.component.scss']
})
export class SystemAdministratorHoteliComponent implements  OnInit {

  @ViewChild('alert', { static: true }) alert: ElementRef;

  public form: FormGroup;
  public name: AbstractControl;
  public address: AbstractControl;
  public description: AbstractControl;
  public administratorHotela: AbstractControl;

  administratoriHotela = []
  alertMessage: string;
  showAlert: boolean = false;

  constructor(protected  router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
              public hotelService: HotelService,
              public userService: UserService) {
    this.form = this.fb.group({
      'name': ['', Validators.compose([Validators.required])],
      'address': ['', Validators.compose([Validators.required])],
      'description': [''],
      'administratorHotela': ['', Validators.compose([Validators.required])]
    })

    this.name = this.form.controls['name'];
    this.address = this.form.controls['address'];
    this.description = this.form.controls['description'];
    this.administratorHotela = this.form.controls['administratorHotela'];

  }

  public ngOnInit() {

    this.showAlert = false;

    this.userService.getHotelAdministrators().subscribe(data => {
      this.administratoriHotela = data;
    })
  }

  confirmClick() {
    this.registerHotel();
  }

  registerHotel() {
    const hotel = new HotelModel(
      this.name.value,
      this.address.value,
      this.description.value,
      this.administratorHotela.value,
    );

    this.hotelService.registerHotel(hotel).subscribe(data => {
      if (data == null) {
        this.alertMessage = 'Hotel nije uspešno registrovan! Pokušajte ponovo.';
      } else {
        this.alertMessage = 'Hotel je uspešno registrovan!';
      }
      this.showAlert = true;
      //this.redirectTo('/systemAdminPage');
    });
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }

  exit() {
    this.redirectTo('/systemAdminPage');
  }

  closeAlert() {
    //this.showAlert = false;
    //this.alert.nativeElement.classList.remove('show');
    this.redirectTo('/systemAdminPage');
  }
}

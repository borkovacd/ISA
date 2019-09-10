import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RoomReservationModel} from "../model/roomReservation.model";
import {AdditionalServicesReservationModel} from "../model/additionalServicesReservation.model";



const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class HotelReservationService {

  private BASE_URL = 'http://localhost:8080/api/hotelReservation';

  constructor(private http: HttpClient) {

  }

  createHotelReservation(object: RoomReservationModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/create`, body, {headers});
  }


  addToHotelReservation(object: AdditionalServicesReservationModel, idRezervacije: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/addToRes/additional/${idRezervacije}`, body, {headers});
  }

  getReservation(idRezervacije: any) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getReservation/${idRezervacije}`, {headers});
  }

  createOrChangeFastHotelReservation(idRezervacijeLeta: any, idHotela: any, idRoom: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createFast/${idRezervacijeLeta}/${idHotela}/${idRoom}`, {headers});
  }

  listaHotelRezervacijaKorisnik(): Observable<any>
  {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/listaHotelRezervacijaKorisnik`, {headers});
  }

  otkaziRezervacijuHotela(id: number): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/otkaziRezervacijuHotela/${id}`,  {headers});
  }
}

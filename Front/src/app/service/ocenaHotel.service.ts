import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {OcenaHotelModel} from "../model/ocenaHotel.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class OcenaHotelService {
  private BASE_URL = 'api/ocenaHotel';

  constructor(private http: HttpClient) {

  }

  oceniHotel(object: OcenaHotelModel, idHotel: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type' : 'application/json'});
    return this.http.post(`${this.BASE_URL}/oceniHotel/${idHotel}`, body, {headers});
  }

  getProsecnaOcenaHotel(idHotel: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getProsecnaOcenaHotel/${idHotel}`, {headers});
  }

  // DA LI KORISNIK IMA MOGUCNOST DA KOMENTARISE
  dozvoljenoOcenjivanjeHotel(idHotel: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/dozvoljenoOcenjivanje/${idHotel}`,  {headers});
  }

}

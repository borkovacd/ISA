import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import { HotelModel } from '../model/hotel.model';
import {Observable} from 'rxjs';
import {SearchHotelsModel} from '../model/searchHotels.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'text/plain'}),
};

@Injectable()
export class HotelService {

  private BASE_URL = 'http://localhost:8080/api/hotel';

  constructor(private http: HttpClient) {

  }

  registerHotel(object: HotelModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/registerHotel`, body, {headers});
  }

  getHotelsByAdministrator(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getHotelsByAdministrator/2`, {headers});
  }

  checkIfReservedHotel(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedHotel/${id}`, {headers});
  }

  izmeniHotel(object: HotelModel, id: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/izmeniHotel/${id}`, body, {headers});
  }

  getHotel(idHotela: any): Observable<any>  {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getHotel/${idHotela}`, {headers});
  }

  getAllHotels(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllHotels`, {headers});
  }

  searchHotels(object: SearchHotelsModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/searchHotels`, body, {headers});
  }
}


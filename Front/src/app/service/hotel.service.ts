import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import { HotelModel } from '../model/hotel.model';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'text/plain'}),
};

@Injectable()
export class HotelService {

  private BASE_URL = 'https://localhost:8080/api/hotel';

  constructor(private http: HttpClient) {

  }

  registerHotel(object: HotelModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/registerHotel`, body, {headers});
  }


}

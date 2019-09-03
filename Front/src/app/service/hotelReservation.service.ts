import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RoomReservationModel} from "../model/roomReservation.model";



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
    return this.http.post(`${this.BASE_URL}/hotelReservation/4`, body, {headers});
  }


}


import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RoomModel} from '../model/room.model';
import {TimePeriodModel} from '../model/timePeriod.model';
import {CheckAvailabilityModel} from '../model/checkAvailability.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class RoomService {

  private BASE_URL = 'http://localhost:8080/api/room';

  constructor(private http: HttpClient) {

  }

  createRoom(object: RoomModel, idHotela: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createRoom/${idHotela}`, body, {headers});
  }

  getAllRooms(idHotela: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllRooms/${idHotela}`, {headers});
  }

  checkIfReservedRoom(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedRoom/${id}`, {headers});
  }

  deleteRoom(idHotela: any, idRoom: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/deleteRoom/${idRoom}`, {headers});
  }

  getRoom(idRoom: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getRoom/${idRoom}`, {headers});
  }

  editRoom(object: RoomModel, idRoom: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/editRoom/${idRoom}`, body, {headers});
  }

  getAvailableRooms(object: TimePeriodModel, idHotela: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/getAvailableRooms/${idHotela}`, body, {headers});
  }

  checkAvailability(object: CheckAvailabilityModel, idHotela: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/checkAvailability/${idHotela}`, body, {headers});
  }

  staviNaPopust(idRoom: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/staviNaPopust/${idRoom}`, {headers});
  }


}

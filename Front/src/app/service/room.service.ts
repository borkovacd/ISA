
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RoomModel} from '../model/room.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class RoomService {

  private BASE_URL = 'http://localhost:8080/api/room';

  constructor(private http: HttpClient) {

  }

  /*createRoom(object: RoomModel, idAccomodation: any): Observable<any> {
    const body = JSON.stringify(object);
    const token = localStorage.getItem('agentId');
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'token': token});
    return this.http.post(`${this.BASE_URL}/createRoom/${idAccomodation}/${token}`, body, {headers: headers});
  }*/

  getAllRooms(idHotel: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllRooms/${idHotel}`, {headers});
  }

  checkIfReservedRoom(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedRoom/${id}`, {headers});
  }

  deleteRoom(idHotela: any, idRoom: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/deleteRoom/${idHotela}/${idRoom}`, {headers});
  }

  /*getOneRoom(idA: any, idR: any): Observable<any> {
    const token = localStorage.getItem('agentId');
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'token': token});
    return this.http.get(`${this.BASE_URL}/getOneRoom/${idA}/${idR}/${token}`, {headers: headers});
  }

  editRoom(object: RoomModel, idAccomodation: any, id: any): Observable<any> {
    const body = JSON.stringify(object);
    const token = localStorage.getItem('agentId');
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'token': token});
    return this.http.put(`${this.BASE_URL}/editRoom/${idAccomodation}/${id}/${token}`, body, {headers: headers});
  }*/

}

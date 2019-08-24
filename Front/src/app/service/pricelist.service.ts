import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {RoomModel} from '../model/room.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class PricelistService {

  private BASE_URL = 'http://localhost:8080/api/pricelist';

  constructor(private http: HttpClient) {

  }

  getAllPricelists(idHotela: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllPricelists/${idHotela}`, {headers});
  }

  checkIfReservedRoom(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedRoom/${id}`, {headers});
  }

  deleteRoom(idHotela: any, idRoom: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/deleteRoom/${idRoom}`, {headers});
  }

  getPricelist(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getPricelist/${idPriceList}`, {headers});
  }

  editRoom(object: RoomModel, idRoom: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/editRoom/${idRoom}`, body, {headers});
  }

}

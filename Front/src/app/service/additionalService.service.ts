import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class AdditionalServiceService {

  private BASE_URL = 'http://localhost:8080/api/additionalService';

  constructor(private http: HttpClient) {

  }

  /*createRoom(object: RoomModel, idHotela: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createRoom/${idHotela}`, body, {headers});
  }*/

  getAllAdditionalServices(idHotela: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllAdditionalServices/${idHotela}`, {headers});
  }

  /*deleteRoom(idHotela: any, idRoom: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/deleteRoom/${idRoom}`, {headers});
  }*/


}

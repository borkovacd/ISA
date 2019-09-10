import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {OcenaRentModel} from "../model/ocenaRent.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class OcenaRentService {
  private BASE_URL = 'api/ocenaRent';

  constructor(private http: HttpClient) {

  }

  oceniRent(object: OcenaRentModel, idRent: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type' : 'application/json'});
    return this.http.post(`${this.BASE_URL}/oceniRent/${idRent}`, body, {headers});
  }

  getProsecnaOcenaRent(idRent: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getProsecnaOcenaRent/${idRent}`, {headers});
  }

}

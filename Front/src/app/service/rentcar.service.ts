import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {RentCarModel} from '../model/rentcar.model';
import {Observable} from 'rxjs';
import {SearchHotelsModel} from "../model/searchHotels.model";
import {SearchRentsModel} from "../model/searchRents.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'text/plain'}),
};

@Injectable()
export class RentCarService {

  private BASE_URL = 'http://localhost:8080/api/rentCar';

  constructor(private http: HttpClient) {

  }

  getAllRents(): Observable<any>
  {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllRents`, {headers});
  }

  registerRentCar(object: RentCarModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/registerRentCar`, body, {headers});
  }

  getRentsByAdministrator(): Observable<any>
  {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getRentsByAdministrator/3`, {headers});
  }

  izmeniRent(object: RentCarModel, id: any) : Observable<any>
  {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/izmeniRent/${id}`, body, {headers});
  }

  getRent(idRent: any): Observable<any>
  {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getRent/${idRent}`, {headers});
  }

  // provera da li u rent-a-car-u ima rezervisanih vozila
  checkIfReservedRent(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedRent/${id}`, {headers});
  }

  // pretraga servisa na osnovu parametara
  searchRents(object: SearchRentsModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/searchRents`, body, {headers});
  }


}

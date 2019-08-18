import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {RentCarModel} from '../model/rentcar.model';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'text/plain'}),
};

@Injectable()
export class RentCarService {

  private BASE_URL = 'http://localhost:8080/api/rentCar';

  constructor(private http: HttpClient) {

  }

  registerRentCar(object: RentCarModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/registerRentCar`, body, {headers});
  }


}

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class UserService {
  private BASE_URL = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) {

  }

  getHotelAdministrators(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getHotelAdministrators`, {headers});
  }

  getRentCarAdministrators(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getRentCarAdministrators`, {headers});
  }

  getAviokompanijaAdministrators(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAviokompanijaAdministrators`, {headers});
  }

  getRegularUsers(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getRegularUsers`, {headers});
  }

  getAdministrators(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAdministrators`, {headers});
  }

  changeRole(id: any, novaUloga: string): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/changeRole/${id}`, novaUloga, {headers});
  }
}

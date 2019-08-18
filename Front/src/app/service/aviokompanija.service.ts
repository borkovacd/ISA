import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {AviokompanijaModel} from '../model/aviokompanija.model';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'text/plain'}),
};

@Injectable()
export class AviokompanijaService {

  private BASE_URL = 'http://localhost:8080/api/aviokompanija';

  constructor(private http: HttpClient) {

  }

  registerAviokompanija(object: AviokompanijaModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/registerAviokompanija`, body, {headers});
  }


}

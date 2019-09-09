import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {OcenaVoziloModel} from "../model/ocenaVozilo.model";
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class OcenaVoziloService {
  private BASE_URL = 'api/ocenaVozilo';

  constructor(private http: HttpClient) {

  }

  oceniVozilo(object: OcenaVoziloModel, idVozilo: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type' : 'application/json'});
    return this.http.post(`${this.BASE_URL}/oceniVozilo/${idVozilo}`, body, {headers});
  }

  getProsecnaOcenaVozila(idVozilo: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getProsecnaOcenaVozila/${idVozilo}`, {headers});
  }

  vratiListuOcenaVozila(idVozila: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/vratiListuOcenaVozila/${idVozila}`, {headers});
  }
}

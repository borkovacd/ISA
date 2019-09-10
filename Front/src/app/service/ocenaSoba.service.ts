import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {OcenaSobaModel} from "../model/ocenaSoba.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class OcenaSobaService {
  private BASE_URL = 'api/ocenaSoba';

  constructor(private http: HttpClient) {

  }

  oceniSobu(object: OcenaSobaModel, idSoba: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type' : 'application/json'});
    return this.http.post(`${this.BASE_URL}/oceniSobu/${idSoba}`, body, {headers});
  }

  getProsecnaOcenaSoba(idSoba: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getProsecnaOcenaSoba/${idSoba}`, {headers});
  }

  // DA LI KORISNIK IMA MOGUCNOST DA KOMENTARISE
  dozvoljenoOcenjivanjeSoba(idSoba: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/dozvoljenoOcenjivanje/${idSoba}`,  {headers});
  }

}

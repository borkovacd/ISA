import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {LokacijaModel} from '../model/lokacija.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class LokacijaService{

  private BASE_URL = 'http://localhost:8080/api/filijala';

  constructor(private http: HttpClient)
  {

  }

  // dodaje novu filijalu
  dodajFilijalu(object: LokacijaModel, idRentACar: any) : Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/dodajFilijalu/${idRentACar}`, body, {headers});

  }

  // vraca sve filijale jednog RentACar-a
  getFilijaleRentACar(idRent: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getFilijaleRentACar/${idRent}`, {headers});
  }

  // obrisi filijalu
  obrisiFilijalu(idRent: any, idLok: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/obrisiFilijalu/${idRent}/${idLok}`, {headers});
  }

  // vraca jednu filijalu preko id-ja
  vratiJednuFilijalu(idRent: any, idLok: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/vratiJednuFilijalu/${idRent}/${idLok}`, {headers});
  }

  // izmena filijale
  izmeniFilijalu(object: LokacijaModel, idRent: any, idLok: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/izmeniFilijalu/${idRent}/${idLok}`, body, {headers});
  }

  checkIfReservedLokacija(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedLokacija/${id}`, {headers});
  }

}

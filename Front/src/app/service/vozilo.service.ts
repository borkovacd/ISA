
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {VoziloModel} from '../model/vozilo.model';
import {TimePeriodModel} from '../model/timePeriod.model';
import {KorisnikModel} from "../model/Korisnik.model";
import {CheckAvailabilityModel} from "../model/checkAvailability.model";
import {CheckAvailabilityRentModel} from "../model/checkAvailabilityRent.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class VoziloService {

  private BASE_URL = 'http://localhost:8080/api/vozilo';

  constructor(private http: HttpClient) {

  }

  // dodaje novo vozilo
  dodajVozilo(object: VoziloModel, idRentACar: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/dodajVozilo/${idRentACar}`, body, {headers});

  }

  // vraca sva vozila jednog RentACar-a
  getVozilaRentACar(idRent: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getVozilaRentACar/${idRent}`, {headers});
  }

  // vraca vozila korisnika
  vratiVozilaKorisnika(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/vratiVozilaKorisnika/4`, {headers});
  }


  // provera da li je vozilo rezervisano
  checkIfReservedVozilo(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedVozilo/${id}`, {headers});
  }

  // obrisi vozilo
  obrisiVozilo(idRent: any, idVozilo: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/obrisiVozilo/${idRent}/${idVozilo}`, {headers});
  }

  // vraca jedno vozilo preko id-ja
  vratiJednoVozilo(idRent: any, idVozilo: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/vratiJednoVozilo/${idRent}/${idVozilo}`, {headers});
  }

  // izmena vozila
  izmeniVozilo(object: VoziloModel, idRent: any, idVozilo: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/izmeniVozilo/${idRent}/${idVozilo}`, body, {headers});
  }

  // vraca slobodna vozila za dat vremenski period
  getAvailableVozila(object: TimePeriodModel, idRent: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/getAvailableVozila/${idRent}`, body, {headers});
  }

  // proveri dostupnost
  checkAvailabilityVozilo(object: CheckAvailabilityRentModel, idRent: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/checkAvailabilityVozilo/${idRent}`, body, {headers});
  }
}


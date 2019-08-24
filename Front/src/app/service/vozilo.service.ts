
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {VoziloModel} from '../model/vozilo.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class VoziloService{

  private BASE_URL = 'http://localhost:8080/api/vozilo';

  constructor(private http: HttpClient)
  {

  }

  // dodaje novo vozilo
  dodajVozilo(object: VoziloModel, idRentACar: any) : Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/dodajVozilo/${idRentACar}`, body, {headers});

  }

  // vraca sva vozila jednog RentACar-a
  getVozilaRentACar(idRent: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getVozilaRentACar/${idRent}`, {headers});
  }

  // vraca sva vozila
  vratiSvaVozila(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getVozilaRentACar`, {headers});
  }


  // provera da li je vozilo rezervisano


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

  // vraca sva vozila jednog korisnika

}

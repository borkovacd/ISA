import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {VoziloReservationModel} from "../model/voziloReservation.model";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class VoziloReservationService {

  private BASE_URL = 'api/voziloReservation';
  private BASE_URL2 = 'api/ocenaVozilo';
  private BASE_URL3 = 'api/ocenaRent';

  constructor(private http: HttpClient) {

  }

  voziloReservation(object: VoziloReservationModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/rezervisiVozilo`, body, {headers});
  }

  listaRentRezervacijaKorisnik(): Observable<any>
  {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/listaRentRezervacijaKorisnik`, {headers});
  }

  otkaziRezervacijuVozila(id: number): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/otkaziRezervacijuVozila/${id}`,  {headers});
  }

  // BRZA REZERVACIJA
  createFastResRent(idRezervacijeLeta: any, idRent: any, idVozilo: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createFastResRent/${idRezervacijeLeta}/${idRent}/${idVozilo}`, {headers});
  }

  // DA LI KORISNIK IMA MOGUCNOST DA KOMENTARISE
  dozvoljenoOcenjivanje(idVozilo: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL2}/dozvoljenoOcenjivanje/${idVozilo}`,  {headers});
  }

  // DA LI KORISNIK IMA MOGUCNOST DA KOMENTARISE
  dozvoljenoOcenjivanjeRent(idRent: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL3}/dozvoljenoOcenjivanje/${idRent}`,  {headers});
  }

}

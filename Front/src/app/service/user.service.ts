import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {KorisnikModel} from '../model/Korisnik.model';
import {HotelModel} from '../model/hotel.model';
import {KorisnikProfilModel} from '../model/korisnikProfil.model';

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

  getKorisnikData(): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getKorisnikData/2`, {headers});
  }

  editUser(object: KorisnikProfilModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(`${this.BASE_URL}/editUser/2`, body, {headers});
  }


  // Olga

  register(k: KorisnikModel) {
    return this.http.post<KorisnikModel>(`${this.BASE_URL}/register`, k);
  }

  verifikujNalog(mail: string) {
    return this.http.get<KorisnikModel>(`${this.BASE_URL}/verifikujNalog/` + mail);
  }

  logIn(k: KorisnikModel) {
    return this.http.post<KorisnikModel>('http://localhost:8080/api/user/logIn', k);
  }

  logOut() {
    return this.http.get<KorisnikModel>('http://localhost:8080/api/user/logOut');
  }

  changePassword(k : KorisnikModel){
    return this.http.post<KorisnikModel>('http://localhost:8080/api/user/promenaLozinke', k);
  }

  vratiTrenutnogKorisnika(){
    return this.http.get<KorisnikModel>('http://localhost:8080/api/user/currentUser');
  }





}

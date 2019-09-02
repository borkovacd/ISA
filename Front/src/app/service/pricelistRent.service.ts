import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PricelistRentModel} from "../model/pricelistRent.model";
import {PricelistModel} from "../model/pricelist.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json',}),
};

@Injectable()
export class PricelistRentService {
  private BASE_URL = 'http://localhost:8080/api/pricelistRent';

  constructor(private http: HttpClient) {

  }

  // vraca sve cenovnike jednog servisa
  getAllPricelistsRent(idRent: any) : Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllPricelistsRent/${idRent}`, {headers});
  }

  // vraca cenovnik na osnovu id-ja
  getPricelistRent(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getPricelistRent/${idPriceList}`, {headers});
  }

  // kreira novi cenovnik
  createPricelistRent(object: PricelistRentModel, idRent: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createPricelistRent/${idRent}`, body, {headers});
  }

  // vraca tipove vozila u rent-a-caru
  getRoomTypesInRent(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getRoomTypesInRent/${idPriceList}`, {headers});
  }

  // vraca aktivan cenovnik
  getActivePricelistRent(idRent: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getActivePricelistRent/${idRent}`, {headers});
  }

  // brise cenovnik
  obrisiCenovnik(idRent: any, idCenovnik: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/obrisiCenovnik/${idRent}/${idCenovnik}`, {headers});
  }





  }

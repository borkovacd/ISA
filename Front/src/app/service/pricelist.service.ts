import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PricelistModel} from '../model/pricelist.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json',}),
};
@Injectable()
export  class PricelistService {

  private BASE_URL = 'http://localhost:8080/api/pricelist';

  constructor(private http: HttpClient) {

  }

  getAllPricelists(idHotela: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllPricelists/${idHotela}`, {headers});
  }

  getPricelist(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getPricelist/${idPriceList}`, {headers});
  }

  createPricelist(object: PricelistModel, idHotela: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createPricelist/${idHotela}`, body, {headers});
  }

  getRoomTypesInHotel(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getRoomTypesInHotel/${idPriceList}`, {headers});
  }

  getAdditionalServiceTypesInHotel(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type' : 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAdditionalServiceTypesInHotel/${idPriceList}`, {headers});
  }
}

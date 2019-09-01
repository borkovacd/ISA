import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PriceRentModel} from "../model/priceRent.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export class PriceRentService{
  private BASE_URL = 'http://localhost:8080/api/priceRent';

  constructor(private http: HttpClient) {

  }

  // vraca sve cene iz cenovnika
  getAllPricesRent(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllPricesRent/${idPriceList}`, {headers});
  }

  // kreira novu cenu u cenovnika
  createPriceRent(object: PriceRentModel, idPriceList: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createPriceRent/${idPriceList}`, body, {headers});

  }


}

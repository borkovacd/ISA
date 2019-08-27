import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PriceModel} from '../model/price.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class PriceService {

  private BASE_URL = 'http://localhost:8080/api/price';

  constructor(private http: HttpClient) {

  }

  getAllPrices(idPriceList: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllPrices/${idPriceList}`, {headers});
  }

  createPrice(object: PriceModel, idPriceList: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createPrice/${idPriceList}`, body, {headers});

  }

}

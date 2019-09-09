import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AdditionalServiceModel} from '../model/additionalService.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable()
export  class AdditionalServiceService {

  private BASE_URL = 'http://localhost:8080/api/additionalService';

  constructor(private http: HttpClient) {

  }

  getAllAdditionalServices(idHotela: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAllAdditionalServices/${idHotela}`, {headers});
  }

  deleteAdditionalService(idAdditionalService: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.delete(`${this.BASE_URL}/deleteAdditionalService/${idAdditionalService}`, {headers});
  }


  createAdditionalService(object: AdditionalServiceModel, idHotela: any): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/createAdditionalService/${idHotela}`, body, {headers});

  }

  getAvailableAdditionalServices(idHotela: any, idRezervacije: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/getAvailableAdditionalServices/${idHotela}/${idRezervacije}`, {headers});
  }

  checkIfReservedService(id: any): Observable<any> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get(`${this.BASE_URL}/checkIfReservedService/${id}`, {headers});
  }
}

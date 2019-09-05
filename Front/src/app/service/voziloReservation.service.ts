import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {VoziloReservationModel} from "../model/voziloReservation.model";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};
@Injectable()
export  class VoziloReservationService {

  private BASE_URL = 'http://localhost:8080/api/voziloReservation';

  constructor(private http: HttpClient) {

  }

  voziloReservation(object: VoziloReservationModel): Observable<any> {
    const body = JSON.stringify(object);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.BASE_URL}/voziloReservation/4`, body, {headers});
  }


}

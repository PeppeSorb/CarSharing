import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rental } from '../interfaces/rental';

const rentalUrl = 'http://localhost:8080/api/rental';

@Injectable({
  providedIn: 'root'
})
export class RentalsService {

  constructor(private http: HttpClient) { }

  getRentals(): Observable<Rental[]>{
    return this.http.get<Rental[]>(rentalUrl);
  }
}

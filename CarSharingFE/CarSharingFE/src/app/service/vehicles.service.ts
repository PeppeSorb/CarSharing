import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehicle } from '../interfaces/vehicle';

const vehicleUrl = 'http://localhost:8080/api/vehicle';

@Injectable({
  providedIn: 'root'
})
export class VehiclesService {

  constructor(private http: HttpClient) { }
  
  getVehicles(): Observable<Vehicle[]>{
    return this.http.get<Vehicle[]>(vehicleUrl);
  }
}

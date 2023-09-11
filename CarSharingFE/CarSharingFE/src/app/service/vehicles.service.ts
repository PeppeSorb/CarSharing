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

  postVehicle(vehicle: Vehicle): Observable<Vehicle>{
    
    var model = null;
    if(vehicle.idModel !== undefined){
        model = vehicle.idModel;
    }
    const jsonData = {
      "id": vehicle.id,
      "licensePlate": vehicle.licensePlate,
      "idModel": {
        "idMod": model
      },
      "country": vehicle.country,
      "region": vehicle.region,
      "city": vehicle.city,
      "street": vehicle.street,
      "houseNumber": vehicle.houseNumber,
      "booked": vehicle.booked,
      "latitude": vehicle.latitude,
      "longitude": vehicle.longitude
    };
    return this.http.post(vehicleUrl, jsonData);
    //return this.http.post<Vehicle>(vehicleUrl, vehicle);
  }

  updateVehicle(id: number, vehicle: Vehicle): Observable<Vehicle>{
    const url = `${vehicleUrl}/${id}`;
    return this.http.put<Vehicle>(url, vehicle);
  }

  deleteVehicle(id:number): Observable<void>{
    const url = `${vehicleUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}

import { Component } from '@angular/core';
import { Vehicle } from 'src/app/interfaces/vehicle';
import { VehiclesService } from 'src/app/service/vehicles.service';

@Component({
  selector: 'app-update-vehicle-form',
  templateUrl: './update-vehicle-form.component.html',
  styleUrls: ['./update-vehicle-form.component.css']
})
export class UpdateVehicleFormComponent {

  a = ''; b = ''; c = '';
  d = ''; e = ''; f = '';
  g = ''; h = '';

  constructor(private service: VehiclesService){}

  updateVehicle(id: number, vehicle: Vehicle){
    this.service.updateVehicle(id, vehicle)
    .subscribe((response)=>{
      console.log(response);
    })
  }
}

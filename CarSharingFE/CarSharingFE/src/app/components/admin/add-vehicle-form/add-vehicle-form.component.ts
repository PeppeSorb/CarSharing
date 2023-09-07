import { Component } from '@angular/core';
import { Vehicle } from 'src/app/interfaces/vehicle';
import { VehiclesService } from 'src/app/service/vehicles.service';

@Component({
  selector: 'app-add-vehicle-form',
  templateUrl: './add-vehicle-form.component.html',
  styleUrls: ['./add-vehicle-form.component.css']
})
export class AddVehicleFormComponent {

  b = ''; c = ''; d = '';
  e = ''; f = ''; g = '';
  h = ''; a = false;

  aa = ''; bb = ''; cc = '';
  dd = ''; ee = ''; ff = '';
  gg = 0; hh = 0;

  constructor(private service: VehiclesService){}

  postVehicle(vehicle: Vehicle){
    this.service.postVehicle(vehicle)
    .subscribe((response)=>{
      console.log(response)
    })
  }
}

import { Component } from '@angular/core';
import { idModel } from 'src/app/interfaces/idModel';
import { Vehicle } from 'src/app/interfaces/vehicle';
import { VehiclesService } from 'src/app/service/vehicles.service';

@Component({
  selector: 'app-add-vehicle-form',
  templateUrl: './add-vehicle-form.component.html',
  styleUrls: ['./add-vehicle-form.component.css']
})
export class AddVehicleFormComponent {

  c = '';
  b = ''; d = '';
  e = ''; f = ''; g = '';
  h = ''; a = false;

  aa = 'prova'; bb = 'prova'; cc = 222;
  dd = 'prova'; ee?: boolean; ff = 'prova.jpg';
  gg = 0; hh = 0;

  // idModel?: idModel = {};

  constructor(private service: VehiclesService){}

  postVehicle(vehicle: Vehicle){
    this.service.postVehicle(vehicle)
    .subscribe((response)=>{
      console.log(response)
    })
  }
}

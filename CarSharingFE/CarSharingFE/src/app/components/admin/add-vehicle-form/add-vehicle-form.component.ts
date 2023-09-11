import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { idModel } from 'src/app/interfaces/idModel';
import { Vehicle } from 'src/app/interfaces/vehicle';
import { VehiclesService } from 'src/app/service/vehicles.service';

@Component({
  selector: 'app-add-vehicle-form',
  templateUrl: './add-vehicle-form.component.html',
  styleUrls: ['./add-vehicle-form.component.css']
})
export class AddVehicleFormComponent{

  form!: FormGroup

  c = '';
  b = ''; d = '';
  e = ''; f = ''; g = '';
  h = ''; a = false;

  aa = 'prova'; bb = 'prova'; cc = 222;
  dd = 'prova'; ee?: boolean; ff = 'prova.jpg';
  gg = 0; hh = 0;

  // dati!:idModel

  // idModValue = new Object.create(this.dati);

  constructor(private service: VehiclesService){}

  postVehicle(form: Vehicle){
    this.service.postVehicle(form)
    .subscribe((response)=>{
      console.log(response)
    })
  }

  options: string[] = [ 'A', 'B', 'C'];
  
  opzioni!: idModel

  // myObject = {
  //   idModel: {idMod: null}
  // };

  display(idModel: idModel): string {
    return idModel && idModel.idMod ? idModel.idMod : '';
  }

  ngOnInit(): void {
      this.form = new FormGroup({
        licensePlate: new FormControl(),
        idModel: new FormControl(),
        country: new FormControl(),
        region: new FormControl(),
        city: new FormControl(),
        street: new FormControl(),
        houseNumber: new FormControl(),
        booked: new FormControl(false),
      })
  }
}

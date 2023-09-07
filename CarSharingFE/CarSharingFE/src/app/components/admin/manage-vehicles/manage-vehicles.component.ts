import { Component, OnInit } from '@angular/core';
import { VehiclesService } from 'src/app/service/vehicles.service';

@Component({
  selector: 'app-manage-vehicles',
  templateUrl: './manage-vehicles.component.html',
  styleUrls: ['./manage-vehicles.component.css']
})
export class ManageVehiclesComponent implements OnInit {

  constructor(private service: VehiclesService){}

  deleteVehicle(id:number){
    if(confirm('Sei sicuro di voler eliminare questo dato ?') == true){
      this.service.deleteVehicle(id)
      .subscribe((response)=>{
        console.log(response);
        this.ngOnInit();
      })
    }
  }

  data: any
  ngOnInit(): void {
    this.service.getVehicles().subscribe(
      response =>{
      console.log(response);
      this.data = response
    })
  }
}

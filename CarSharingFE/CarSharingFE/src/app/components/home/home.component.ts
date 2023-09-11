import { Component, OnInit } from '@angular/core';
import { VehiclesService } from 'src/app/service/vehicles.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  constructor(private service: VehiclesService){}

  data: any
  ngOnInit(): void {
      this.service.getVehicles()
      .subscribe((response)=>{
        response = this.data
        console.log(response)
      })
  }
}

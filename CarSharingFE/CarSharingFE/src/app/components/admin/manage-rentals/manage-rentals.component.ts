import { Component, OnInit } from '@angular/core';
import { RentalsService } from 'src/app/service/rentals.service';

@Component({
  selector: 'app-manage-rentals',
  templateUrl: './manage-rentals.component.html',
  styleUrls: ['./manage-rentals.component.css']
})
export class ManageRentalsComponent implements OnInit{

  constructor(private service: RentalsService){}

  deleteRental(id:number){
    if(confirm('Sei sicuro di voler eliminare questo dato ?') == true){
      this.service.deleteRental(id)
      .subscribe((response)=>{
        console.log(response);
        this.ngOnInit();
      })
    }
  }

  data: any
  ngOnInit(): void {
    this.service.getRentals().subscribe(
      response =>{
      console.log(response)
      this.data = response
    })
  }
}

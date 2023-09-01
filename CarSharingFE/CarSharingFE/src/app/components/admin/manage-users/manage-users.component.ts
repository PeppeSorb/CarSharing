import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/service/users.service';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  constructor(private service: UsersService){}

  data: any
  ngOnInit(): void {
      this.service.getUsers().subscribe(
        response =>{
        console.log(response)
        this.data = response
      })
  }
}

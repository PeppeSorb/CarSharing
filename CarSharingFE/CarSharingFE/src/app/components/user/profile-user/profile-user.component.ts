import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/service/users.service';

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit{

  constructor(private service: UsersService){}

  data: any
  ngOnInit(): void {
      this.service.getUsers()
      .subscribe(response=>{
        response = this.data;
        console.log(this.data)
      })
  }
}

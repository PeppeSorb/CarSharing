import { Component } from '@angular/core';
import { Userr } from 'src/app/interfaces/user';
import { UsersService } from 'src/app/service/users.service';

@Component({
  selector: 'app-update-user-form',
  templateUrl: './update-user-form.component.html',
  styleUrls: ['./update-user-form.component.css']
})
export class UpdateUserFormComponent {

  a = ''; b = ''; c = ''; d = '';
  e = ''; f = ''; g = ''; h = false;
  i = 'profile1.jpg'; l = 0;

  constructor(private service: UsersService){}

  updateUser(id: number, user: Userr){
    this.service.updateUser(id, user)
    .subscribe((response)=>{
      console.log(response)
    })
  }
}

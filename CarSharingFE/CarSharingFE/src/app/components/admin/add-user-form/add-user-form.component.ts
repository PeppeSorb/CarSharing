import { Component } from '@angular/core';
import { Userr } from 'src/app/interfaces/user';
import { UsersService } from 'src/app/service/users.service';

@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.css']
})
export class AddUserFormComponent {

  b = ''; c = '';
  d = ''; e = '';
  f = ''; g = '';
  a = false; h = 'profile1.jpg';
  i = 0;

  constructor(private service: UsersService){}

  postUser(user: Userr){
    this.service.postUser(user)
    .subscribe((response) =>{
      console.log(response)
    })
  }
}

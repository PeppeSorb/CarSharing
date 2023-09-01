import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent {

  a = ''; b = ''

  // constructor(private authService: AuthService){}

  // login(body: {email: string, password: string}){
  //   this.http.post('', body)
  //   .subscribe((res)=>{
  //     console.log(res)
  //   })
  // }

  // login(form: NgForm){
    // this.authService.loggedIn(form.value) deve essere un oggetto
    // .subscribe((data)=>{
    //   console.log(data)
    // })

  login(form: NgForm){
    console.log(form.value.email, form.value.password)
  }
}

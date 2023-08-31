import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  a = ''; b = ''; c = ''
  d = ''; e = '';f = ''
  
  // constructor(private authService: AuthService){}

  signUp(form: NgForm){
    // this.authService.signedUp(form.value) deve essere un oggetto
    // .subscribe((data)=>{
    //   console.log(data)
    // })
    
    console.log(form.value)
  }
}

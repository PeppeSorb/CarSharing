import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = ''
  isLoggedIn = false
  // user: User

  constructor(private http: HttpClient) { }

  isAuthenticated(){
    return this.isLoggedIn
  }

  // createUser(email: string, id: string, token: string, expirationDate: Date){
  //   this.user = new User(email, id, token, expirationDate)
  // }

  signedUp(body: {}){
    return this.http.post(this.url, body)
  }

  loggedIn(body: {}){
    return this.http.post(this.url, body)
  }
}

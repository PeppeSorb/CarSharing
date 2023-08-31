import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = ''
  isLoggedIn = false

  constructor(private http: HttpClient) { }

  isAuthenticated(){
    return this.isLoggedIn
  }

  signedUp(body: {}){
    return this.http.post(this.url, body)
  }

  loggedIn(body: {}){
    return this.http.post(this.url, body)
  }
}

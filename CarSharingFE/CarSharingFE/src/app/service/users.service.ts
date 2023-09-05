import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Userr } from '../interfaces/user';
import { Observable } from 'rxjs';

const userUrl = 'http://localhost:8080/api/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<Userr[]>{
    return this.http.get<Userr[]>(userUrl);
  }

  postUser(user: Userr): Observable<Userr>{
    return this.http.post<Userr>(userUrl, user);
  }

  updateUser(id: number, user: Userr): Observable<Userr>{
    const url = `${userUrl}/${id}`;
    return this.http.put<Userr>(url, user);
  }

  deleteUser(id:number): Observable<void>{
    const url = `${userUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}

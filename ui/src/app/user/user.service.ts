import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  base = 'http://localhost:8083/'
  endpoint = 'user';

  constructor(private httpClient: HttpClient) { }

  getDefaultUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.base}${this.endpoint}`);
  }
}

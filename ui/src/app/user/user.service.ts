import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  base = environment.baseURLUser;
  endpoint = 'user';

  constructor(private httpClient: HttpClient) { }

  getDefaultUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.base}${environment.userService}/${this.endpoint}`);
  }
}

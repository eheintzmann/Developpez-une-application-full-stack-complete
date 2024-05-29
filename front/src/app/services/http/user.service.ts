import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, take } from "rxjs";
import { User } from "../../interfaces/user.interface";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl: string = 'http://localhost:8080/api/v1/user';

  constructor(private http: HttpClient) { }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}`)
      .pipe(
        take(1)
      );
  }
}

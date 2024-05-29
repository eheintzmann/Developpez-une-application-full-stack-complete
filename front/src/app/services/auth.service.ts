import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BearerToken } from "../interfaces/bearer-token.interface";
import { shareReplay, take } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080/api/v1/auth';

  constructor(private http: HttpClient) {}

  login(login: string, password: string) {
    return this.http.post<BearerToken>(`${this.baseUrl}/login`, {login, password})
      .pipe(
        shareReplay(),
        take(1)
      );
  }

  register(email: string, username: string, password: string) {
    return this.http.post<BearerToken>(`${this.baseUrl}/register`, {email, username, password})
      .pipe(
        shareReplay(),
        take(1)
      );
    }
}

import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BearerToken } from "../interfaces/bearerToken.interface";
import { shareReplay } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080/api/v1/auth';
  passwordPattern: string = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$"

  constructor(private http: HttpClient) {}

  login(login: string, password: string) {
    return this.http.post<BearerToken>(`${this.baseUrl}/login`, {login, password})
      .pipe(shareReplay());
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BearerToken } from "../interfaces/bearerToken";
import { shareReplay } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl:string = 'http://localhost:8080/api/v1/';
  passwordPattern: string = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$"

  constructor(private http: HttpClient) {}

  login(login: string, password: string) {
    return this.http.post<BearerToken>(`${this.baseUrl}auth/login`, {login, password})
      .pipe(shareReplay());
  }
}

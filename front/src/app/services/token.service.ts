import { Injectable } from '@angular/core';
import { BearerToken } from "../interfaces/bearerToken.interface";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private tokenId: string = 'Bearer'

  constructor() { }

  getToken(): string | null {
    return localStorage.getItem(this.tokenId);
  }

  setToken(token: BearerToken): void {
    localStorage.setItem(this.tokenId, token.token);
  }

  removeToken() {
    localStorage.removeItem(this.tokenId);
  }

  isLogged(): boolean {
    return this.getToken() !== null;
  }

}

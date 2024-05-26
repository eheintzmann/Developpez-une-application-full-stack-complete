import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from "../services/token.service";
import { inject } from "@angular/core";

export const authGuard: CanActivateFn = (route, state): boolean => {
  if (inject(TokenService).isLogged()) {
    return true;
  }
  inject(Router).navigate(['login']);
  return false;
};

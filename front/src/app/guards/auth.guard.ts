import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from "../services/token.service";
import { inject } from "@angular/core";
import { LoadingService } from "../services/loading.service";

export const authGuard: CanActivateFn = (): boolean => {
  if (inject(TokenService).isLogged()) {
    return true;
  }
  inject(LoadingService).loadingOn();
  inject(Router).navigate(['/']);
  return false;
};

import { CanActivateFn, Router } from '@angular/router';
import { inject } from "@angular/core";
import { TokenService } from "../services/token.service";
import { LoadingService } from "../services/loading.service";

export const unauthGuard: CanActivateFn = (): boolean => {
  if (inject(TokenService).isLogged()) {
    inject(LoadingService).loadingOn();
    inject(Router).navigate(['/']);
    return false;
  }
  return true;
};

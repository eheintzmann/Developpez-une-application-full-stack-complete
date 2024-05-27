import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from "@angular/core";
import { TokenService } from "../services/token.service";
import { delay } from "rxjs";

export const jwtInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn) => {
  const tokenService: TokenService = inject(TokenService);
  if (tokenService.isLogged()) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${tokenService.getToken()}`,
      }
    })
  }
  return next(req).pipe(delay(5000));
};

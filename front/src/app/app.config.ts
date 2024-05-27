import { ApplicationConfig, ErrorHandler, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from "@angular/material/form-field";
import { provideHttpClient, withInterceptors } from "@angular/common/http";
import { jwtInterceptor } from "./interceptors/jwt.interceptor";
import { GlobalErrorHandler } from "./errrors/global-error-handler";

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync('animations'),
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'outline' } },
    provideHttpClient(
      withInterceptors([jwtInterceptor])
    ),
    { provide: ErrorHandler, useClass: GlobalErrorHandler }
  ]
};

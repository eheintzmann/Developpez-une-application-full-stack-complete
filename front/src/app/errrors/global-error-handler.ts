import { ErrorHandler, Injectable } from "@angular/core";
import { HttpErrorResponse } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { TokenService } from "../services/token.service";
import { DisplayErrorService } from "./display-error.service";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor(
    private _snackBar: MatSnackBar,
    private _tokenService: TokenService,
    private _displayErrorService: DisplayErrorService,
  ) {
  }
  handleError(wrappedError: any): void {
    this._displayErrorService.show(
      this._filterError(wrappedError.rejection ?? wrappedError),'Fermer');
  }

  private _filterError(error: any): string {

    // Navigator OffLIne
    if (!(navigator.onLine)) {
      return 'Le navigateur est hors-ligne'
    }
    if (error instanceof HttpErrorResponse) {
      return this._filterHttpError(error);
    }

    return 'Erreur inconnue';
  }

  private _filterHttpError(error: HttpErrorResponse): string {

    // Invalid Country ID
    if (error.status === 400) {
      return 'Requête invalide'
    }

    if (error.status === 401) {
      this._tokenService.removeToken();
      return 'Vous êtes déconnecté(e)'
    }

    // Other Server Error
    return 'Erreur inconnue';
  }
}

import { ErrorHandler, Injectable } from "@angular/core";
import { HttpErrorResponse } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { TokenService } from "../services/token.service";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor(
    private _snackBar: MatSnackBar,
    private _tokenService: TokenService
  ) {
  }
  handleError(wrappedError: any): void {
    this._snackBar.open(
      this._filterError(wrappedError.rejection ?? wrappedError),
      'Fermer',
      {
        verticalPosition: 'top',
        horizontalPosition:'center'
      }
    );
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
      return 'Mauvaise requête'
    }

    if (error.status === 401) {
      this._tokenService.removeToken();
      return 'Vous êtes déconnecté(e)'
    }

    // Other Server Error
    return 'Erreur inconnue';
  }
}

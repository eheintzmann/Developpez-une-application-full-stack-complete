import { ErrorHandler, Injectable } from "@angular/core";
import { HttpErrorResponse } from "@angular/common/http";
import { TokenService } from "../services/token.service";
import { DisplayErrorService } from "./display-error.service";
import { Router } from "@angular/router";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor(
    private _tokenService: TokenService,
    private _displayErrorService: DisplayErrorService,
    private router: Router,
  ) {
  }
  handleError(wrappedError: any): void {
    this._filterError(wrappedError.rejection ?? wrappedError).then(
      (msg: string) => this._displayErrorService.show(msg, 'Fermer')
    );
  }

  private async _filterError(error: any): Promise<string> {

    // Navigator OffLIne
    if (!(navigator.onLine)) {
      return 'Le navigateur est hors-ligne'
    }
    if (error instanceof HttpErrorResponse) {
      return await this._filterHttpError(error);
    }

    return 'Erreur inconnue';
  }

  private async _filterHttpError(error: HttpErrorResponse): Promise<string> {

    // Invalid Country ID
    if (error.status === 400) {
      return 'Requête invalide'
    }

    if (error.status === 401) {
      this._tokenService.removeToken();
      await this.router.navigate(['/login']);
      return 'Vous êtes déconnecté(e)';
    }

    // Other Server Error
    return 'Erreur inconnue';
  }
}

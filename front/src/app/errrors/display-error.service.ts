import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarRef, TextOnlySnackBar } from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class DisplayErrorService {

  constructor(private _snackBar: MatSnackBar) { }

  show(message: string, action: string): MatSnackBarRef<TextOnlySnackBar> {
    return this._snackBar.open(message, action);
  }

  hide(): void {
    this._snackBar.dismiss()
  }
}

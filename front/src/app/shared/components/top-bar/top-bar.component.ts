import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from "@angular/router";
import { AsyncPipe, NgClass, NgOptimizedImage } from "@angular/common";

import { Observable, tap } from "rxjs";

import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule, MatIconAnchor } from "@angular/material/button";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatDividerModule } from "@angular/material/divider";
import { MatDialog, MatDialogClose, MatDialogConfig } from "@angular/material/dialog";

import { ResponsiveService } from "../../../services/responsive.service";
import { TokenService } from "../../../services/token.service";
import { SideDialogComponent } from "./side-dialog/side-dialog.component";

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [
    AsyncPipe,
    NgOptimizedImage,
    MatIconModule,
    MatIconAnchor,
    RouterLink,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    MatDialogClose,
    RouterLinkActive,
    NgClass,
  ],
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.css'
})
export class TopBarComponent implements OnInit {

  isPhone$!: Observable<boolean>;
  isLogged!: boolean;

  constructor(
    private responsive: ResponsiveService,
    private tokenService: TokenService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {

    this.isLogged = this.tokenService.isLogged();

    this.isPhone$ = this.responsive.isPhone()
      .pipe(
        tap( (isPhone: boolean): void => {
          if (!isPhone) {
            this.dialog.closeAll();
          }
        } )
      );
  }

  openSideDialog() {
    const dialogConfig: MatDialogConfig = new MatDialogConfig();

    dialogConfig.position = {
      right: '0',
      bottom: '0'
    };

    dialogConfig.minWidth = '184px';
    dialogConfig.maxWidth = '184px';
    dialogConfig.width = '184px';

    dialogConfig.minHeight = '100vh';
    dialogConfig.maxHeight = '100vh';
    dialogConfig.height = '100vh';

    dialogConfig.direction = 'rtl';
    dialogConfig.hasBackdrop = true;
    dialogConfig.autoFocus = false;
    dialogConfig.panelClass = "side-panel";
    dialogConfig.closeOnNavigation = true;

    this.dialog.open(SideDialogComponent, dialogConfig);
  }
}

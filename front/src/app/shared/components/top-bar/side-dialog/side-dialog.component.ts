import { Component, OnInit } from '@angular/core';
import { RouterLink } from "@angular/router";
import { MatButtonModule, MatIconButton } from "@angular/material/button";
import { MatDialogModule } from "@angular/material/dialog";
import { MatIconModule } from "@angular/material/icon";
import { AsyncPipe } from "@angular/common";
import { Observable } from "rxjs";
import { ResponsiveService } from "../../../../services/responsive.service";
import { TokenService } from "../../../../services/token.service";

@Component({
  selector: 'app-side-dialog',
  standalone: true,
  imports: [
    RouterLink,
    MatIconModule,
    MatIconButton,
    MatButtonModule,
    MatDialogModule,
    AsyncPipe
  ],
  templateUrl: './side-dialog.component.html',
  styleUrl: './side-dialog.component.css'
})
export class SideDialogComponent implements OnInit {
  isPhone$!: Observable<boolean>;
  isLogged!: boolean;

  constructor(
    private responsive: ResponsiveService,
    private tokenService: TokenService,
  ) { }

  ngOnInit(): void {
    this.isLogged = this.tokenService.isLogged();
    this.isPhone$ = this.responsive.isPhone();
  }
}

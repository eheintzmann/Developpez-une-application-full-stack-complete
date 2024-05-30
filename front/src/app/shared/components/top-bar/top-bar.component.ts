import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { AsyncPipe, NgOptimizedImage } from "@angular/common";
import { MatIconModule } from "@angular/material/icon";
import { MatIconAnchor } from "@angular/material/button";
import { RouterLink } from "@angular/router";
import { ResponsiveService } from "../../../services/responsive.service";

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [
    AsyncPipe,
    MatIconModule,
    NgOptimizedImage,
    MatIconAnchor,
    RouterLink
  ],
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.css'
})
export class TopBarComponent implements OnInit {

  isPhone$!: Observable<boolean>;

  constructor(private responsive: ResponsiveService) { }

  ngOnInit(): void {
    this.isPhone$ = this.responsive.isPhone();
  }
}

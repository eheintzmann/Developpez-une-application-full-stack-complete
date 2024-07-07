import { Component, Input, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { RouterLink } from "@angular/router";
import { MatCardModule } from "@angular/material/card";
import { MatGridListModule } from "@angular/material/grid-list";
import { AsyncPipe, DatePipe } from "@angular/common";
import { Feed } from "../../../../interfaces/feed.interface";
import { ResponsiveService } from "../../../../services/responsive.service";
import { TopBarComponent } from "../../../../shared/components/top-bar/top-bar.component";
import { MatButtonModule } from "@angular/material/button";
import { MatListModule } from "@angular/material/list";
import { ShortenPipe } from "../../../../shared/pipes/shorten.pipe";

@Component({
  selector: 'app-feed',
  standalone: true,
  imports: [
    AsyncPipe,
    RouterLink,
    DatePipe,
    TopBarComponent,
    MatButtonModule,
    MatListModule,
    MatCardModule,
    MatGridListModule,
    ShortenPipe,
  ],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.css'
})
export class FeedComponent implements OnInit {

  @Input() public feed!: Feed | null;

  isPhone$!: Observable<boolean>;

  constructor(
    private responsive: ResponsiveService   ) {
  }

  ngOnInit(): void {

    this.isPhone$ = this.responsive.isPhone();
  }
}

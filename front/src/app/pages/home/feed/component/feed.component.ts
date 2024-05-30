import { Component, Input, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { RouterLink } from "@angular/router";
import { MatCardModule } from "@angular/material/card";
import { MatGridListModule } from "@angular/material/grid-list";
import { AsyncPipe, DatePipe } from "@angular/common";
import { Feed } from "../../../../interfaces/feed.interface";
import { ResponsiveService } from "../../../../services/responsive.service";

@Component({
  selector: 'app-feed',
  standalone: true,
  imports: [MatCardModule, MatGridListModule, AsyncPipe, RouterLink, DatePipe],
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

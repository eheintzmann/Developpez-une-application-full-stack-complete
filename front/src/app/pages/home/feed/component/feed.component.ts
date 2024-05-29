import { Component, Input, OnInit } from '@angular/core';
import { map, Observable } from "rxjs";
import { ActivatedRoute, RouterLink } from "@angular/router";
import { BreakpointObserver, Breakpoints, BreakpointState } from "@angular/cdk/layout";
import { MatCardModule } from "@angular/material/card";
import { MatGridListModule } from "@angular/material/grid-list";
import { AsyncPipe, DatePipe } from "@angular/common";
import { Feed } from "../../../../interfaces/feed.interface";

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
    private route: ActivatedRoute,
    private responsive: BreakpointObserver,
  ) {
  }

  ngOnInit(): void {

    this.isPhone$ = this.responsive.observe([
      Breakpoints.XSmall
    ]).pipe(
      map((result: BreakpointState) => {
        return result.matches;
      })
    )
  }
}

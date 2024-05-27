import { Component, OnInit } from '@angular/core';
import { map, Observable } from "rxjs";
import { Feed } from "../../../interfaces/feed.interface";
import { AsyncPipe, DatePipe, JsonPipe } from "@angular/common";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatCardModule } from "@angular/material/card";
import { BreakpointObserver, Breakpoints, BreakpointState } from "@angular/cdk/layout";
import { ActivatedRoute, Data, RouterLink } from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    MatGridListModule,
    MatCardModule,
    DatePipe,
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  isPhone$!: Observable<boolean>;
  feed$!: Observable<Feed>

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

    this.feed$ = this.route.data
      .pipe(
        map((data: Data) => data['feed'])
      );
  }
}

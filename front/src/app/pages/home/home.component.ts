import { Component, OnInit } from '@angular/core';
import { map, Observable } from "rxjs";
import { Feed } from "../../interfaces/feed.interface";
import { PostService } from "../../services/post.service";
import { AsyncPipe, DatePipe, JsonPipe } from "@angular/common";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatCardModule } from "@angular/material/card";
import { BreakpointObserver, Breakpoints, BreakpointState } from "@angular/cdk/layout";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    MatGridListModule,
    MatCardModule,
    DatePipe
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  isPhone$!: Observable<boolean>;
  feed$!: Observable<Feed>

  constructor(
    private postService: PostService,
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

    this.feed$ = this.postService.getFeed();


  }
}

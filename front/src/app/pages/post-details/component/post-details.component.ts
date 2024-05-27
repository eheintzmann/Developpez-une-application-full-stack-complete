import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data } from "@angular/router";
import { BreakpointObserver, Breakpoints, BreakpointState } from "@angular/cdk/layout";
import { map, Observable } from "rxjs";
import { PostService } from "../../../services/post.service";
import { PostWithComments } from "../../../interfaces/post-with-comments.interface";
import { AsyncPipe, JsonPipe } from "@angular/common";

@Component({
  selector: 'app-post-details',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.css'
})
export class PostDetailsComponent implements OnInit {

  isPhone$!: Observable<boolean>;
  post$!: Observable<PostWithComments>


  constructor(
    private route: ActivatedRoute,
    private responsive: BreakpointObserver,
    private postService: PostService
  ) {}

  ngOnInit(): void {

    this.isPhone$ = this.responsive.observe([
      Breakpoints.XSmall
    ]).pipe(
      map((result: BreakpointState) => {
        return result.matches;
      })
    )

    this.post$ = this.route.data
      .pipe(
        map((data: Data) => data['post'])
      );
  }
}

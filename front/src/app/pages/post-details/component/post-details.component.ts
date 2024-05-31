import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data } from "@angular/router";
import { map, Observable } from "rxjs";
import { PostWithComments } from "../../../interfaces/post-with-comments.interface";
import { AsyncPipe, JsonPipe } from "@angular/common";
import { ResponsiveService } from "../../../services/responsive.service";
import { TopBarComponent } from "../../../shared/components/top-bar/top-bar.component";

@Component({
  selector: 'app-post-details',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    TopBarComponent
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.css'
})
export class PostDetailsComponent implements OnInit {

  isPhone$!: Observable<boolean>;
  post$!: Observable<PostWithComments>

  constructor(
    private route: ActivatedRoute,
    private responsive: ResponsiveService
  ) {}

  ngOnInit(): void {

    this.isPhone$ = this.responsive.isPhone();

    this.post$ = this.route.data
      .pipe(
        map((data: Data) => data['post'])
      );
  }
}

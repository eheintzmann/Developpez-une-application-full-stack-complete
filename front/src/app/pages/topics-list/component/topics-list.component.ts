import { Component, OnInit } from '@angular/core';
import { map, Observable } from "rxjs";
import { ActivatedRoute, Data, RouterLink } from "@angular/router";
import { BreakpointObserver, Breakpoints, BreakpointState } from "@angular/cdk/layout";
import { Topics } from "../../../interfaces/topics.interface";
import { TopicService } from "../../../services/http/topic.service";
import { AsyncPipe, DatePipe } from "@angular/common";
import { MatCardModule } from "@angular/material/card";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatIcon } from "@angular/material/icon";
import { MatButton, MatIconButton } from "@angular/material/button";
import { MatSuffix } from "@angular/material/form-field";

@Component({
  selector: 'app-topics-list',
  standalone: true,
  imports: [
    AsyncPipe,
    DatePipe,
    MatCardModule,
    MatGridListModule,
    RouterLink,
    MatIcon,
    MatIconButton,
    MatSuffix,
    MatButton
  ],
  templateUrl: './topics-list.component.html',
  styleUrl: './topics-list.component.css'
})
export class TopicsListComponent implements OnInit {
  isPhone$!: Observable<boolean>;
  topics$!: Observable<Topics>


  constructor(
    private route: ActivatedRoute,
    private responsive: BreakpointObserver,
    private topicService: TopicService
  ) {}

  ngOnInit(): void {

    this.isPhone$ = this.responsive.observe([
      Breakpoints.XSmall
    ]).pipe(
      map((result: BreakpointState) => {
        return result.matches;
      })
    )

    this.topics$ = this.route.data
      .pipe(
        map((data: Data) => data['topics'])
      );
  }
}

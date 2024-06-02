import { Component, OnDestroy, OnInit } from '@angular/core';
import { map, Observable, Subject, takeUntil, tap } from "rxjs";
import { ActivatedRoute, Data, RouterLink } from "@angular/router";
import { Topics } from "../../../interfaces/topics.interface";
import { AsyncPipe, DatePipe } from "@angular/common";
import { MatCardModule } from "@angular/material/card";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatIcon } from "@angular/material/icon";
import { MatButton, MatIconButton } from "@angular/material/button";
import { MatSuffix } from "@angular/material/form-field";
import { ResponsiveService } from "../../../services/responsive.service";
import { TopBarComponent } from "../../../shared/components/top-bar/top-bar.component";
import { LoadingService } from "../../../services/loading.service";
import { TopicService } from "../../../services/http/topic.service";
import { Topic } from "../../../interfaces/topic.interface";

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
    MatButton,
    TopBarComponent
  ],
  templateUrl: './topics-list.component.html',
  styleUrl: './topics-list.component.css'
})
export class TopicsListComponent implements OnInit, OnDestroy {
  isPhone$!: Observable<boolean>;
  topics$!: Observable<Topics>;
  private destroy$!: Subject<boolean>;



  constructor(
    private route: ActivatedRoute,
    private responsive: ResponsiveService,
    private loadingService: LoadingService,
    private topicService: TopicService,
  ) {}

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  ngOnInit(): void {
    this.destroy$ = new Subject<boolean>();

    this.isPhone$ = this.responsive.isPhone();

    this.topics$ = this.route.data
      .pipe(
        map((data: Data) => data['topics'])
      );
  }

  onClick(topicId: number): void {
    this.loadingService.loadingOn();
    this.topicService
      .subscribeTo(topicId)
      .pipe(
        tap((): void => {
          this.topics$ = this.topics$.pipe(
            map((topics: Topics): Topics => {
              topics.topics.map((topic: Topic): Topic => {
                if (topic.id == topicId) {
                  topic.subscribed = true;
                }
                return topic;
              })
              return topics;
            })
          );
          this.loadingService.loadingOff();
        }),
        takeUntil(this.destroy$)
      ).subscribe();
  }
}

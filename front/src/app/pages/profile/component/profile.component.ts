import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscriptions } from "../../../interfaces/subscriptions.interface";
import { map, Observable, Subject, takeUntil, tap } from "rxjs";
import { ActivatedRoute, Data, Router } from "@angular/router";
import { AsyncPipe, JsonPipe } from "@angular/common";
import { User } from "../../../interfaces/user.interface";
import { TokenService } from "../../../services/token.service";
import { LoadingService } from "../../../services/loading.service";
import { ResponsiveService } from "../../../services/responsive.service";
import { TopBarComponent } from "../../../shared/components/top-bar/top-bar.component";
import { MatDividerModule } from "@angular/material/divider";
import { MatButton } from "@angular/material/button";
import { Topic } from "../../../interfaces/topic.interface";
import { TopicService } from "../../../services/http/topic.service";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    TopBarComponent,
    MatDividerModule,
    MatButton
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit, OnDestroy {

  isPhone$!: Observable<boolean>;
  user$!: Observable<User>;
  subscriptions$!: Observable<Subscriptions>;
  private destroy$!: Subject<boolean>;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tokenService: TokenService,
    private loadingService: LoadingService,
    private responsive: ResponsiveService,
    private topicService: TopicService,
  ) {
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  ngOnInit(): void {
    this.destroy$ = new Subject<boolean>();

    this.isPhone$ = this.responsive.isPhone();

    this.subscriptions$ = this.route.data
      .pipe(
        map((data: Data) => data['subscriptions'])
      );

    this.user$ = this.route.data
      .pipe(
        map((data: Data) => data['user'])
      );
  }

  onDisconnect(): void {
    this.loadingService.loadingOn();
    this.tokenService.removeToken();
    this.router.navigate(['/'])
  }

  onUnsubscribe(topicId: number): void {
    this.loadingService.loadingOn();
    this.topicService
      .unSubscribeTo(topicId)
      .pipe(
        tap((): void => {
          this.subscriptions$ = this.subscriptions$.pipe(
            map((subscriptions: Subscriptions): Subscriptions => {
              subscriptions.subscriptions = subscriptions.subscriptions.filter((topic: Topic): boolean => topic.id != topicId)
              return subscriptions
            })
          );
          this.loadingService.loadingOff();
        }),
        takeUntil(this.destroy$)
      ).subscribe();
  }
}

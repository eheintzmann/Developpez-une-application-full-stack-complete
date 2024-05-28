import { Component, Input, OnInit } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { LoadingService } from '../../../services/loading.service';
import { Observable, tap } from 'rxjs';
import {
  NavigationCancel,
  NavigationEnd,
  NavigationError,
  NavigationStart,
  Router
} from '@angular/router';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-loading-indicator',
  standalone: true,
  imports: [
    AsyncPipe,
    MatProgressSpinnerModule,
    FormsModule,
  ],
  templateUrl: './loading-indicator.component.html',
  styleUrl: './loading-indicator.component.css'
})
export class LoadingIndicatorComponent implements OnInit {

  @Input()
  detectNavigation: boolean = false;

  loading$: Observable<boolean>;

  constructor(
    private router: Router,
    private loadingService: LoadingService
  ) {
    this.loading$ = this.loadingService.loading$;
  }

  ngOnInit(): void {
    if (this.detectNavigation) {
      this.router.events
        .pipe(
          tap({
            next: (event): void => {
              if (event instanceof NavigationStart) {
                this.loadingService.loadingOn();
              } else if (
                event instanceof NavigationEnd ||
                event instanceof NavigationError ||
                event instanceof NavigationCancel
              ) {
                this.loadingService.loadingOff();
              }
            },
            error: (): void => {
              this.loadingService.loadingOff();
            }

          })
        )
        .subscribe()
    }
  }
}

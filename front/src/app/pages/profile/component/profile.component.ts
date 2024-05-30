import { Component, OnInit } from '@angular/core';
import { Subscriptions } from "../../../interfaces/subscriptions.interface";
import { map, Observable } from "rxjs";
import { ActivatedRoute, Data, Router } from "@angular/router";
import { AsyncPipe, JsonPipe } from "@angular/common";
import { User } from "../../../interfaces/user.interface";
import { TokenService } from "../../../services/token.service";
import { LoadingService } from "../../../services/loading.service";
import { ResponsiveService } from "../../../services/responsive.service";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {

  isPhone$!: Observable<boolean>;
  user$!: Observable<User>;
  subscriptions$!: Observable<Subscriptions>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tokenService: TokenService,
    private loadingService: LoadingService,
    private responsive: ResponsiveService
  ) {
  }

  ngOnInit(): void {
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

}

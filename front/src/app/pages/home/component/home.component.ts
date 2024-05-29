import { Component, OnInit } from '@angular/core';
import { AsyncPipe, DatePipe, JsonPipe } from "@angular/common";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatCardModule } from "@angular/material/card";
import { ActivatedRoute, Data, RouterLink } from "@angular/router";
import { FeedComponent } from "../feed/component/feed.component";
import { TokenService } from "../../../services/token.service";
import { map, Observable } from "rxjs";
import { Feed } from "../../../interfaces/feed.interface";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    MatGridListModule,
    MatCardModule,
    DatePipe,
    RouterLink,
    FeedComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  isLogged!: boolean;
  feed$!: Observable<Feed> ;

  constructor(
    private route: ActivatedRoute,
    private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.isLogged = this.tokenService.isLogged();

    this.feed$ = this.route.data
      .pipe(
        map((data: Data) => data['feed'])
      );
  }
}

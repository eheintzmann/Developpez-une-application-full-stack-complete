import { Component } from '@angular/core';
import { AsyncPipe, DatePipe, JsonPipe } from "@angular/common";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatCardModule } from "@angular/material/card";
import { RouterLink } from "@angular/router";
import { FeedComponent } from "../feed/feed.component";

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
export class HomeComponent {

}

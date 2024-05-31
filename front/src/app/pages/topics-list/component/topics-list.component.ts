import { Component, OnInit } from '@angular/core';
import { map, Observable } from "rxjs";
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
export class TopicsListComponent implements OnInit {
  isPhone$!: Observable<boolean>;
  topics$!: Observable<Topics>


  constructor(
    private route: ActivatedRoute,
    private responsive: ResponsiveService,
  ) {}

  ngOnInit(): void {

    this.isPhone$ = this.responsive.isPhone();

    this.topics$ = this.route.data
      .pipe(
        map((data: Data) => data['topics'])
      );
  }
}

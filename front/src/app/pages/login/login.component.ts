import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from "@angular/cdk/layout";
import { map, Observable } from "rxjs";
import { AsyncPipe, NgOptimizedImage } from "@angular/common";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule, MatIconButton } from "@angular/material/button";
import { MatGridListModule } from "@angular/material/grid-list";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    AsyncPipe,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatIconButton,
    MatGridListModule,
    NgOptimizedImage,
    MatButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  isPhone$!: Observable<boolean>;
  loginForm!: FormGroup;
  passwordPattern: string = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$"

  constructor(
    private responsive: BreakpointObserver,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.isPhone$ = this.responsive.observe([
      Breakpoints.XSmall
    ]).pipe(
      map((result: BreakpointState) => {
        return result.matches;
      })
    )

    this.loginForm = this.formBuilder.group({
      login: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
      password: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
    })
  }
}

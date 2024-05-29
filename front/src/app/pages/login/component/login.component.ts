import { Component, OnInit } from '@angular/core';
import { AsyncPipe } from "@angular/common";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { TopBarComponent } from "../../../shared/components/top-bar/top-bar.component";
import { AuthService } from "../../../services/auth.service";
import { BearerToken } from "../../../interfaces/bearer-token.interface";
import { TokenService } from "../../../services/token.service";
import { Router } from "@angular/router";
import { catchError, EMPTY, tap } from "rxjs";
import { DisplayErrorService } from "../../../errrors/display-error.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    AsyncPipe,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    TopBarComponent,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  error: string = '';
  hide: boolean = true;

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private formBuilder: FormBuilder,
    private router: Router,
    private displayErrorService: DisplayErrorService,
  ) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      login: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
      password: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
    })
  }

  onSubmit(): void {
    this.authService
      .login(this.loginForm.value.login, this.loginForm.value.password)
      .pipe(
        tap((bearer: BearerToken): void => {
          this.displayErrorService.hide();
          this.tokenService.setToken(bearer);
          this.router.navigate([''])
        }),
        catchError(err => {
          if (err.status && err.status === 401) {
            this.displayErrorService.show('Identifiants invalides', 'Fermer');
            return EMPTY;
          }
          throw err;
        })
      )
      .subscribe();
  }
}

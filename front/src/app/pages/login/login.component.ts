import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgOptimizedImage } from "@angular/common";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule, MatIconButton } from "@angular/material/button";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatCardModule } from "@angular/material/card";
import { TopBarComponent } from "../../shared/top-bar/top-bar.component";
import { AuthService } from "../../services/auth.service";
import { BearerToken } from "../../interfaces/bearer-token.interface";
import { TokenService } from "../../services/token.service";
import { Router } from "@angular/router";

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
    TopBarComponent,
    MatCardModule,
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
    private router: Router
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
      .subscribe({
        next: (bearer: BearerToken): void => {
          this.error = '';
          this.tokenService.setToken(bearer);
          this.router.navigate(['']);
        },
        error: err => {
          if (err.status) {
            if (err.status === 401) {
              this.error = 'Identifiants invalides';
              return
            } else if (err.status === 400) {
              this.error = 'Mauvaise requête';
              return;
            }
          }
          this.error = 'Erreur inconnue';
        }
      });
  }
}

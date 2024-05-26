import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgOptimizedImage } from "@angular/common";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule, MatIconButton } from "@angular/material/button";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatCardModule } from "@angular/material/card";
import { tap } from "rxjs";
import { TopBarComponent } from "../../shared/top-bar/top-bar.component";
import { AuthService } from "../../services/auth.service";
import { BearerToken } from "../../interfaces/bearerToken";

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
  error: string =  '';
  hide: boolean = true;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder
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
    this.authService.login(this.loginForm.value.login, this.loginForm.value.password)
      .pipe(
        tap({
            next: (bearer: BearerToken): void => {
              this.error = '';
              console.log('Token: ' + bearer.token)
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
          }
        )
      ).subscribe();
  }
}

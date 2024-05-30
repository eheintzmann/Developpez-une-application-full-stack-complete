import { Component, OnDestroy, OnInit } from '@angular/core';
import { AsyncPipe } from "@angular/common";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { TopBarComponent } from "../../../../shared/components/top-bar/top-bar.component";
import { AuthService } from "../../../../services/http/auth.service";
import { BearerToken } from "../../../../interfaces/bearer-token.interface";
import { TokenService } from "../../../../services/token.service";
import { Router } from "@angular/router";
import { catchError, EMPTY, Subject, takeUntil, tap } from "rxjs";
import { DisplayErrorService } from "../../../../errrors/display-error.service";
import { LoadingService } from "../../../../services/loading.service";

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
export class LoginComponent implements OnInit, OnDestroy {

  loginForm!: FormGroup;
  error: string = '';
  hide: boolean = true;
  disabled: boolean = false;

  private destroy$!: Subject<boolean>;


  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private formBuilder: FormBuilder,
    private router: Router,
    private displayErrorService: DisplayErrorService,
    private loadingService: LoadingService,
  ) {
  }

  ngOnInit(): void {
    this.destroy$ = new Subject<boolean>();

    this.loginForm = this.formBuilder.group({
      login: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
      password: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
    })
  }

  ngOnDestroy():void {
    this.destroy$.next(true);
    this.displayErrorService.hide();
  }

  onSubmit(): void {
    this.loadingService.loadingOn();
    this.authService
      .login(this.loginForm.value.login, this.loginForm.value.password)
      .pipe(
        tap((bearer: BearerToken): void => {
          this.resetForm(this.loginForm);
          this.tokenService.setToken(bearer);
          this.router.navigate([''])
        }),
        catchError(err => {
          if (err.status && err.status === 401) {
            this.loadingService.loadingOff();
            this.displayErrorService.show('Identifiants invalides', 'Fermer');
            return EMPTY;
          }
          throw err;
        }),
        takeUntil(this.destroy$)
      )
      .subscribe();

    this.loadingService.loading$
      .pipe(
        tap(
          (value: boolean) :void => { this.disabled = value }
        ),
        takeUntil(this.destroy$)
      ).subscribe();
  }

  resetForm(form: FormGroup): void {
    form.reset();
    Object.keys(form.controls).forEach((key: string): void => {
      form.controls[key].setErrors(null);
    });
  }
}

import { Component, OnDestroy, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { AuthService } from "../../../../services/http/auth.service";
import { TokenService } from "../../../../services/token.service";
import { Router } from "@angular/router";
import { BearerToken } from "../../../../interfaces/bearer-token.interface";
import { MatButton, MatButtonModule } from "@angular/material/button";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { TopBarComponent } from "../../../../shared/components/top-bar/top-bar.component";
import { catchError, EMPTY, Subject, takeUntil, tap } from "rxjs";
import { DisplayErrorService } from "../../../../errrors/display-error.service";
import { LoadingService } from "../../../../services/loading.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    MatButton,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    TopBarComponent
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit, OnDestroy {

  hide: boolean = true;
  registerForm!: FormGroup;
  disabled: boolean = false;


  private passwordPattern: string = '^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$'
  private usernamePattern: string = '^[a-zA-Z0-9_-]{3,}$'
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

    this.registerForm = this.formBuilder.group({
      email: [
        null, [Validators.required, Validators.email, Validators.maxLength(255)]
      ],
      username: [
        null, [Validators.required, Validators.pattern(this.usernamePattern), Validators.maxLength(255)]
      ],
      password: [
        null, [Validators.required, Validators.pattern(this.passwordPattern), Validators.maxLength(255)]
      ],
    })
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.displayErrorService.hide();
  }


  onSubmit(): void {
    this.loadingService.loadingOn();
    this.authService
      .register(this.registerForm.value.email, this.registerForm.value.username, this.registerForm.value.password)
      .pipe(
        tap((bearer: BearerToken): void => {
          this.resetForm(this.registerForm);
          this.tokenService.setToken(bearer);
          this.router.navigate(['']);
        }),
        catchError(err => {
          if (err.status && err.status === 400) {
            this.loadingService.loadingOff()
            this.displayErrorService.show('Nom d\'utilisateur ou e-email indisponible', 'Fermer');
            return EMPTY;
          }
          throw err;
        }),
        takeUntil(this.destroy$)
      ).subscribe();

    this.loadingService.loading$
      .pipe(
        tap((value: boolean) :void => {
          this.disabled = value
        }),
        takeUntil(this.destroy$)
      ).subscribe();

  }

  getErrorMessage(ctrl: AbstractControl): string {
    if (ctrl.hasError('required')) {
      return 'Ce champ est requis';
    } else if (ctrl.hasError('maxlength')) {
      return '255 caractères maximum';
    } else if (ctrl.hasError('email')) {
      return 'Entrez une adresse e-mail valide';
    } else if (ctrl.hasError('pattern')) {
      return ctrl === this.registerForm.controls['username'] ?
        'Entrez un nom d\'utilisateur valide' :
        'Entrez un mot de passe valide';
    } else {
      return 'Ce champs contient une erreur';
    }
  }

  resetForm(form: FormGroup): void {
    form.reset();
    Object.keys(form.controls).forEach((key: string): void => {
      form.controls[key].setErrors(null);
    });
  }
}

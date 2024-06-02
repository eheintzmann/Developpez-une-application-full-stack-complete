import { Component, OnDestroy, OnInit } from '@angular/core';
import { catchError, EMPTY, map, Observable, Subject, takeUntil, tap } from "rxjs";
import { Topics } from "../../../interfaces/topics.interface";
import { ActivatedRoute, Data, Router, RouterLink } from "@angular/router";
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatAnchor, MatButton, MatIconButton } from "@angular/material/button";
import { MatError, MatFormField, MatSuffix } from "@angular/material/form-field";
import { MatIcon } from "@angular/material/icon";
import { MatInput } from "@angular/material/input";
import { TopBarComponent } from "../../../shared/components/top-bar/top-bar.component";
import { MatSelectModule } from "@angular/material/select";
import { AsyncPipe } from "@angular/common";
import { LoadingService } from "../../../services/loading.service";
import { ResponsiveService } from "../../../services/responsive.service";
import { PostService } from "../../../services/http/post.service";
import { PostWithComments } from "../../../interfaces/post-with-comments.interface";
import { DisplayErrorService } from "../../../errrors/display-error.service";

@Component({
  selector: 'app-create-post',
  standalone: true,
  imports: [
    MatButton,
    MatError,
    MatFormField,
    MatIcon,
    MatIconButton,
    MatInput,
    MatSuffix,
    ReactiveFormsModule,
    TopBarComponent,
    MatSelectModule,
    AsyncPipe,
    MatAnchor,
    RouterLink
  ],
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent implements OnInit, OnDestroy {

  topics$!: Observable<Topics>;
  newPostForm!: FormGroup;
  disabled: boolean = false;
  isPhone$!: Observable<boolean>;
  private destroy$!: Subject<boolean>;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private responsive: ResponsiveService,
    private loadingService: LoadingService,
    private postService: PostService,
    private displayErrorService: DisplayErrorService,
  ) {
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.displayErrorService.hide();
  }

  ngOnInit(): void {

    this.destroy$ = new Subject<boolean>();

    this.isPhone$ = this.responsive.isPhone();

    this.topics$ = this.route.data
      .pipe(
        map((data: Data) => data['topics'])
      );

    this.newPostForm = this.formBuilder.group({
      topic: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
      title: [
        null, [Validators.required, Validators.maxLength(255)]
      ],
      content: [
        null, [Validators.required]
      ],
    })
  }

  getErrorMessage(ctrl: AbstractControl): string {
    if (ctrl.hasError('required')) {
      return 'Ce champ est requis';
    } else if (ctrl.hasError('maxlength')) {
      return '255 caractères maximum';
    } else {
      return 'Ce champs contient une erreur';
    }
  }

  onSubmit(): void {
    console.log(this.newPostForm.value)

    this.loadingService.loadingOn();
    this.postService
      .postPost(this.newPostForm.value.title, this.newPostForm.value.content, +this.newPostForm.value.topic)
      .pipe(
        tap((post: PostWithComments): void => {
          this.resetForm(this.newPostForm);
          this.router.navigate([`/post-details/${post.id}`]);
        }),
        catchError(err => {
          if (err.status && err.status === 400) {
            this.loadingService.loadingOff()
            this.displayErrorService.show('Ce titre a déjà été utilisé', 'Fermer');
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

  resetForm(form: FormGroup): void {
    form.reset();
    Object.keys(form.controls).forEach((key: string): void => {
      form.controls[key].setErrors(null);
    });
  }

}

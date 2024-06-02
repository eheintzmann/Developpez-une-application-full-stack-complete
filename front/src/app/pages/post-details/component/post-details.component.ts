import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Data } from "@angular/router";
import { map, Observable, Subject, takeUntil, tap } from "rxjs";
import { PostWithComments } from "../../../interfaces/post-with-comments.interface";
import { AsyncPipe, JsonPipe } from "@angular/common";
import { ResponsiveService } from "../../../services/responsive.service";
import { TopBarComponent } from "../../../shared/components/top-bar/top-bar.component";
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatButton } from "@angular/material/button";
import { MatError, MatFormField, MatLabel } from "@angular/material/form-field";
import { MatInput } from "@angular/material/input";
import { MatOption } from "@angular/material/core";
import { MatSelect } from "@angular/material/select";
import { LoadingService } from "../../../services/loading.service";
import { CommentService } from "../../../services/http/comment.service";
import { PostService } from "../../../services/http/post.service";

@Component({
  selector: 'app-post-details',
  standalone: true,
  imports: [
    AsyncPipe,
    JsonPipe,
    TopBarComponent,
    FormsModule,
    MatButton,
    MatError,
    MatFormField,
    MatInput,
    MatLabel,
    MatOption,
    MatSelect,
    ReactiveFormsModule
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.css'
})
export class PostDetailsComponent implements OnInit, OnDestroy {

  isPhone$!: Observable<boolean>;
  post$!: Observable<PostWithComments>
  commentForm!: FormGroup;
  disabled: boolean = false;
  private destroy$!: Subject<boolean>;


  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private responsive: ResponsiveService,
    private loadingService: LoadingService,
    private postService: PostService,
    private commentService: CommentService,
  ) {}

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  ngOnInit(): void {
    this.destroy$ = new Subject<boolean>();

    this.isPhone$ = this.responsive.isPhone();

    this.post$ = this.route.data
      .pipe(
        map((data: Data) => data['post'])
      );

    this.commentForm = this.formBuilder.group({
      comment: [
        null, [Validators.required]
      ],
    })
  }

  getErrorMessage(ctrl: AbstractControl): string {
    if (ctrl.hasError('required')) {
      return 'Ce champ est requis';
    } else {
      return 'Ce champs contient une erreur';
    }
  }

  onComment(postId: number): void {
    this.loadingService.loadingOn();
    this.commentService
      .postComment(this.commentForm.value.comment, postId)
      .pipe(
        tap((): void => {
          this.resetForm(this.commentForm);
          this.post$ = this.postService.getPostById(postId).pipe(
            tap(() => this.loadingService.loadingOff())
          );
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

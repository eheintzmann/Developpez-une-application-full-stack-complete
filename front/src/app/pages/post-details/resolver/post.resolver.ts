import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { inject } from "@angular/core";
import { Observable } from "rxjs";
import { PostService } from "../../../services/http/post.service";
import { PostWithComments } from "../../../interfaces/post-with-comments.interface";

export const postResolver: ResolveFn<PostWithComments> = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PostWithComments> => {
  const postId: string | null = route.paramMap.get('id');
  return inject(PostService).getPostById(+postId!);
};

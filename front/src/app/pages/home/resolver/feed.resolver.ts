import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { Observable } from "rxjs";
import { Feed } from "../../../interfaces/feed.interface";
import { inject } from "@angular/core";
import { PostService } from "../../../services/http/post.service";

export const feedResolver: ResolveFn<Feed> = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Feed> => {
  return inject(PostService).getFeed();
};

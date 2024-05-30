import { ResolveFn } from '@angular/router';
import { Observable, of } from "rxjs";
import { Feed } from "../../../../interfaces/feed.interface";
import { inject } from "@angular/core";
import { PostService } from "../../../../services/http/post.service";
import { TokenService } from "../../../../services/token.service";

export const feedResolver: ResolveFn<Feed|null> = (): Observable<Feed|null> => {

  if (inject(TokenService).isLogged()) {
    return inject(PostService).getFeed();
  } else {
    return of(null);
  }
};

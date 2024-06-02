import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, shareReplay, take } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  baseUrl: string = 'http://localhost:8080/api/v1/comments';
  constructor(private http: HttpClient) {
  }

  postComment(content: string, postId: number): Observable<void>{
    return this.http.post<void>(`${this.baseUrl}`, { content: content, post_id: postId })
      .pipe(
        shareReplay(),
        take(1)
      );
  }
}

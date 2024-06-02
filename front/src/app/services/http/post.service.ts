import { Injectable } from '@angular/core';
import { Feed } from "../../interfaces/feed.interface";
import { HttpClient } from "@angular/common/http";
import { Observable, shareReplay, take } from "rxjs";
import { PostWithComments } from "../../interfaces/post-with-comments.interface";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  baseUrl: string = 'http://localhost:8080/api/v1/posts';

  constructor(private http: HttpClient) {
  }

  getFeed(): Observable<Feed> {
    return this.http.get<Feed>(`${this.baseUrl}/user`)
      .pipe(
        take(1)
      );
  }

  getPostById(postId: number): Observable<PostWithComments> {
    return this.http.get<PostWithComments>(`${this.baseUrl}/${postId}`)
      .pipe(
        take(1)
      );
  }

  postPost(title: string, content: string, topicId: number): Observable<PostWithComments> {
    return this.http.post<PostWithComments>(
      this.baseUrl,
      {title: title, content: content, topic_id: topicId}
    )
      .pipe(
        shareReplay(),
        take(1)
      )
  }
}

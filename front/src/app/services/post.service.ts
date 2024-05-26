import { Injectable } from '@angular/core';
import { Feed } from "../interfaces/feed.interface";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PostWithComments } from "../interfaces/post-with-comments.interface";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  baseUrl: string = 'http://localhost:8080/api/v1/posts';

  constructor(private http: HttpClient) { }

  getFeed(): Observable<Feed> {
    return this.http.get<Feed>(`${this.baseUrl}/user`);
  }

  getPostById(postId: number): Observable<PostWithComments> {
    return this.http.get<PostWithComments>(`${this.baseUrl}/${postId}`);
  }
}

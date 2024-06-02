import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, shareReplay, take } from "rxjs";
import { Topics } from "../../interfaces/topics.interface";
import { Subscriptions } from "../../interfaces/subscriptions.interface";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  baseUrl: string = 'http://localhost:8080/api/v1/topics';

  constructor(private http: HttpClient) { }

  getTopics(): Observable<Topics> {
    return this.http.get<Topics>(`${this.baseUrl}`)
      .pipe(
        take(1)
      );
  }

  getUserTopics(): Observable<Subscriptions> {
    return this.http.get<Subscriptions>(`${this.baseUrl}/user`)
      .pipe(
        take(1)
      );
  }

  subscribeTo(topicId: number): Observable<Subscriptions> {
    return this.http.post<Subscriptions>(`${this.baseUrl}/${topicId}/user`, null)
      .pipe(
        shareReplay(),
        take(1)
      );
  }
}

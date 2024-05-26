import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, shareReplay } from "rxjs";
import { Topics } from "../interfaces/topics.interface";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  baseUrl: string = 'http://localhost:8080/api/v1/topics';

  constructor(private http: HttpClient) { }

  getTopics(): Observable<Topics> {
    return this.http.get<Topics>(`${this.baseUrl}`)
      .pipe(shareReplay());
  }

}

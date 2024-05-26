import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  baseUrl: string = 'http://localhost:8080/api/v1/topics';

  constructor(private http: HttpClient) { }


}

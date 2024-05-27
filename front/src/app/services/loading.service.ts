import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private loadingSubject:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);

  loading$ = this.loadingSubject.asObservable()

  loadingOn() {
    this.loadingSubject.next(true);
  }

  loadingOff() {
    this.loadingSubject.next(false);
  }
}

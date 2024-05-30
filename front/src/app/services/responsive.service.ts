import { Injectable } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from "@angular/cdk/layout";
import { map, Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ResponsiveService  {

  constructor(private responsive: BreakpointObserver) {
  }

  isPhone(): Observable<boolean> {
    return this.responsive.observe([
      Breakpoints.XSmall
    ]).pipe(
      map((result: BreakpointState) => {
        return result.matches;
      })
    )
  }
}

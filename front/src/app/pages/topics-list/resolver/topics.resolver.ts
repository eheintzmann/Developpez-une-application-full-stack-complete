import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { inject } from "@angular/core";
import { TopicService } from "../../../services/http/topic.service";
import { Topics } from "../../../interfaces/topics.interface";
import { Observable } from "rxjs";

export const topicsResolver: ResolveFn<Topics> = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Topics> => {
  return inject(TopicService).getTopics();
};

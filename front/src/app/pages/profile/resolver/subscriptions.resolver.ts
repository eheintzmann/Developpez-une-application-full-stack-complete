import { ResolveFn } from '@angular/router';
import { Subscriptions } from "../../../interfaces/subscriptions.interface";
import { inject } from "@angular/core";
import { TopicService } from "../../../services/http/topic.service";
import { Observable } from "rxjs";

export const subscriptionsResolver: ResolveFn<Subscriptions> = (): Observable<Subscriptions> => {
  return inject(TopicService).getUserTopics();
};

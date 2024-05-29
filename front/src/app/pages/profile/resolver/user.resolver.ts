import { ResolveFn } from '@angular/router';
import { User } from "../../../interfaces/user.interface";
import { Observable } from "rxjs";
import { inject } from "@angular/core";
import { UserService } from "../../../services/http/user.service";

export const userResolver: ResolveFn<User> = (): Observable<User> => {
  return inject(UserService).getCurrentUser();
};

import { Routes } from '@angular/router';
import { authGuard } from "./guards/auth.guard";
import { feedResolver } from "./pages/home/resolver/feed.resolver";
import { topicsResolver } from "./pages/topics-list/resolver/topics.resolver";
import { postResolver } from "./pages/post-details/resolver/post.resolver";

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./pages/login/component/login.component').then(m => m.LoginComponent),
    title: 'MDD Login'
  },
  {
    path:'post-details/:id',
    loadComponent: () => import('./pages/post-details/component/post-details.component').then(m => m.PostDetailsComponent),
    canActivate: [authGuard],
    resolve: { post: postResolver },
    title: 'MDD Article'
  },
  {
    path: 'topics',
    loadComponent: () => import('./pages/topics-list/component/topics-list.component').then(m => m.TopicsListComponent),
    canActivate: [authGuard],
    resolve: { topics: topicsResolver },
    title: 'MDD THèmes'
  },
  {
    path: '',
    loadComponent:() => import('./pages/home/component/home.component').then(m => m.HomeComponent),
    canActivate: [authGuard],
    resolve: { feed: feedResolver },
    title: 'MDD Home'
  }
];

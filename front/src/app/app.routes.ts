import { Routes } from '@angular/router';
import { authGuard } from "./guards/auth.guard";
import { feedResolver } from "./pages/home/feed/resolver/feed.resolver";
import { topicsResolver } from "./pages/topics-list/resolver/topics.resolver";
import { postResolver } from "./pages/post-details/resolver/post.resolver";
import { subscriptionsResolver } from "./pages/profile/resolver/subscriptions.resolver";
import { userResolver } from "./pages/profile/resolver/user.resolver";
import { unauthGuard } from "./guards/unauth.guard";

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./pages/auth/login/component/login.component').then(m => m.LoginComponent),
    canActivate: [unauthGuard],
    title: 'MDD Connexion',
  },
  {
    path: 'register',
    loadComponent: () => import('./pages/auth/register/component/register.component').then(m => m.RegisterComponent),
    canActivate: [unauthGuard],
    title: 'MDD Inscription'
  },
  {
    path: 'profile',
    loadComponent: () => import('./pages/profile/component/profile.component').then(m => m.ProfileComponent),
    canActivate: [authGuard],
    resolve: { subscriptions: subscriptionsResolver, user: userResolver },
    title: 'MDD Profile'
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
    title: 'MDD Thèmes'
  },
  {
    path: '**',
    loadComponent:() => import('./pages/home/component/home.component').then(m => m.HomeComponent),
    resolve: { feed: feedResolver },
    title: 'MDD Home'
  }
];

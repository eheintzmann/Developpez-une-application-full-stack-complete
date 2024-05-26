import { Routes } from '@angular/router';
import { authGuard } from "./guards/auth.guard";

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent),
    title: 'MDD Login'
  },
  {
    path:'post-details/:id',
    loadComponent: () => import('./pages/post-details/post-details.component').then(m => m.PostDetailsComponent),
    canActivate: [authGuard],
    title: 'MDD Article'
  },
  {
    path: '',
    loadComponent:() => import('./pages/home/home.component').then(m => m.HomeComponent),
    canActivate: [authGuard],
    title: 'MDD Home'
  }
];

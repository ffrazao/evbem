import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGuardService } from './comum/guard/auth-guard.service';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "logout",
    component: LogoutComponent
  },
  {
    path: "pag",
    loadChildren: './pag/pag.module#PagModule',
    canActivate: [AuthGuardService]
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: false, onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './ferramenta/seguranca/guards/auth-guard';

const routes: Routes = [
  { path: 'home', loadChildren: './home/home.module#HomeModule' },
  { path: 'login', loadChildren: './login/login.module#LoginModule' },
  { path: 'crud-1', loadChildren: './crud-1/crud-1.module#Crud1Module', canActivate: [AuthGuard], canLoad: [AuthGuard] },
  { path: 'crud-2', loadChildren: './crud-2/crud-2.module#Crud2Module', canActivate: [AuthGuard], canLoad: [AuthGuard] },
  { path: 'crud-3', loadChildren: './crud-3/crud-3.module#Crud3Module', canActivate: [AuthGuard], canLoad: [AuthGuard] },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', loadChildren: './err-404/err-404.module#Err404Module' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

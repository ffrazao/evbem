import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './comum/servico/seguranca/guard-route/auth-guard.service';

const routes: Routes = [
  /* rotas que não apresentam o menu de opções nem são protegidas por login */
  {
    path: 'login',
    loadChildren: () => import('./comum/componente/login/login.module').then(m => m.LoginPageModule)
  },
  {
    path: 'list',
    loadChildren: () => import('./list/list.module').then(m => m.ListPageModule)
  },
  /* rotas que apresentam o menu de opções */
  {
    path: 'm',
    loadChildren: () => import('./comum/componente/menu/menu.module').then(m => m.MenuPageModule)
  },
  /* rotas que não apresentam o menu de opções mas são protegidos pelo login */
  {
    path: 's',
    children: [
      {
        path: 'list',
        loadChildren: () => import('./list/list.module').then(m => m.ListPageModule)
      },
      {
        path: 'veiculo-registro',
        loadChildren: () => import('./veiculo/registro/registro/registro.module').then(m => m.RegistroComponentModule)
      },
      {
        path: 'veiculo-registrando',
        loadChildren: () => import('./veiculo/registro/registrando/registrando.module').then(m => m.RegistrandoComponentModule)
      },
      {
        path: 'veiculo-parar',
        loadChildren: () => import('./veiculo/registro/parar/parar.module').then(m => m.PararComponentModule)
      },
    ],
    canActivate: [AuthGuard],
    canLoad: [AuthGuard],
  },
  {
    path: '',
    redirectTo: 'm',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

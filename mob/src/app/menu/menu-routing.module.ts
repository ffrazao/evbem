import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../comum/servico/guard-route/auth-guard.service';
import { MenuPage } from './menu.page';

const routes: Routes = [
  {
    /* relacionar aqui todos os links que mostram o menu */
    path: '',
    component: MenuPage,
    children: [
      {
        /* relacionar aqui todos os links protegidos pelo login */
        path: 's',
        children: [
          {
            path: 'veiculo',
            loadChildren: () => import('../veiculo/veiculo.module').then(m => m.VeiculoPageModule)
          },
          {
            path: '',
            redirectTo: 'veiculo',
            pathMatch: 'full'
          },
        ],
        canActivate: [AuthGuard],
        canLoad: [AuthGuard],
      },
      /* relacionar aqui todos os links que mostram o menu mas não são protegidos pelo login */
      {
        path: 'home',
        loadChildren: () => import('../home/home.module').then(m => m.HomePageModule)
      },
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class MenuRoutingModule {}

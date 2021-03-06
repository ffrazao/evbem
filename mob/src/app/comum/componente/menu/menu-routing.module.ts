import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MenuPage } from './menu.page';
import { AuthGuard } from '../../servico/seguranca/guard-route/auth-guard.service';

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
            loadChildren: () => import('../../../funcionalidade/veiculo/veiculo.module').then(m => m.VeiculoPageModule)
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
        loadChildren: () => import('../../../funcionalidade/home/home.module').then(m => m.HomePageModule)
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

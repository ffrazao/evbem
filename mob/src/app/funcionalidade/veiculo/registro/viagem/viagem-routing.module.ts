import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ViagemPage } from './viagem.page';

const routes: Routes = [
  {
    path: '',
    component: ViagemPage,
    children: [
      {
        path: 'saida',
        loadChildren: () => import('./saida/saida.module').then(m => m.SaidaComponentModule),
      },
      {
        path: 'rota',
        loadChildren: () => import('./rota/rota.module').then(m => m.RotaComponentModule),
      },
      {
        path: 'chegada',
        loadChildren: () => import('./chegada/chegada.module').then(m => m.ChegadaComponentModule),
      },
      /*
      {
        path: 'saida',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./saida/saida.module').then(m => m.SaidaComponentModule)
          }
        ]
      },
      {
        path: 'rota',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./rota/rota.module').then(m => m.RotaComponentModule)
          }
        ]
      },
      {
        path: 'chegada',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./chegada/chegada.module').then(m => m.ChegadaComponentModule)
          }
        ]
      },
      */
      {
        path: '',
        redirectTo: 'saida',
        pathMatch: 'full'
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViagemPageRoutingModule { }

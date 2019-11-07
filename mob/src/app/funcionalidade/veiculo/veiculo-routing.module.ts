import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { VeiculoPage } from './veiculo.page';

const routes: Routes = [
  {
    path: '',
    component: VeiculoPage,
    children: [
      {
        path: 'viagem',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./viagem/viagem.module').then(m => m.ViagemPageModule)
          }
        ]
      },
      {
        path: 'historico',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./historico/historico.module').then(m => m.HistoricoPageModule)
          }
        ]
      },
      {
        path: 'tab3',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./tab3/tab3.module').then(m => m.Tab3PageModule)
          }
        ]
      },
      {
        path: '',
        redirectTo: 'viagem',
        pathMatch: 'full'
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VeiculoPageRoutingModule { }

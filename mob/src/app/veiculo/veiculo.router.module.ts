import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VeiculoPage } from './veiculo.page';

const routes: Routes = [
  {
    path: '',
    component: VeiculoPage,
    children: [
      {
        path: 'registro',
        children: [
          {
            path: '',
            loadChildren: () =>
              import('./registro/registro.module').then(m => m.RegistroPageModule)
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
        redirectTo: '/veiculo/registro',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: '/veiculo/registro',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VeiculoPageRoutingModule {}

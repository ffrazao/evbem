import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
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
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [VeiculoPage]
})
export class VeiculoPageModule { }

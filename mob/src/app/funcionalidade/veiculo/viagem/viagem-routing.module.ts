import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViagemPage } from './viagem.page';

const routes: Routes = [
  {
    path: '',
    component: ViagemPage,
    children: [
      {
        path: 'inicio',
        loadChildren: () =>
          import('./inicio/inicio.module').then(m => m.InicioComponentModule)
      },
      {
        path: '',
        redirectTo: 'inicio',
        pathMatch: 'full'
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViagemPageRoutingModule { }

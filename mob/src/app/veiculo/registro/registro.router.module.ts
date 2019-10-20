import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistroPage } from './registro.page';

const routes: Routes = [
  {
    path: '',
    component: RegistroPage,
    children: [
      {
        path: 'inicio',
        loadChildren: () =>
          import('./inicio/inicio.module').then(m => m.InicioComponentModule)
      },
      {
        path: 'registro',
        loadChildren: () =>
          import('./registro/registro.module').then(m => m.RegistroComponentModule)
      },
      {
        path: 'registrando',
        loadChildren: () =>
          import('./registrando/registrando.module').then(m => m.RegistrandoComponentModule)
      },
      {
        path: 'parar',
        loadChildren: () =>
          import('./parar/parar.module').then(m => m.PararComponentModule)
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
export class RegistroPageRoutingModule { }
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'usuario2',
    loadChildren: './usuario2/usuario2.module#Usuario2Module',
    data: {}
  },
  {
    path: 'usuario',
    loadChildren: './usuario/usuario.module#UsuarioModule',
    data: {}
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagRoutingModule { }

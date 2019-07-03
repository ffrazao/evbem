import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PerfilComponent } from './perfil/perfil.component';
import { UsuarioListComponent } from './usuario/usuario-list.component';
import { UsuarioComponent } from './usuario/usuario.component';

const routes: Routes = [
  {
    path: 'usuario/n',
    component: UsuarioComponent
  },
  {
    path: 'usuario/:ids/:pos',
    component: UsuarioComponent
  },
  {
    path: 'usuario/:ids',
    component: UsuarioComponent
  },
  {
    path: 'usuario',
    component: UsuarioListComponent
  },
  {
    path: 'perfil',
    component: PerfilComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagRoutingModule { }

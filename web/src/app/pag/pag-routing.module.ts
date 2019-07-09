import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PerfilComponent } from './perfil/perfil.component';
import { UsuarioListComponent } from './usuario/usuario-list.component';
import { UsuarioComponent } from './usuario/usuario.component';

const routes: Routes = [
  {
    path: 'usuario',
    component: UsuarioListComponent
  },
  {
    path: 'usuario/:pag',
    component: UsuarioListComponent
  },
  {
    path: 'usuario/:pag/n',
    component: UsuarioComponent
  },
  {
    path: 'usuario/:pag/:ids',
    component: UsuarioComponent
  },
  {
    path: 'usuario/:pag/:ids/n',
    component: UsuarioComponent
  },
  {
    path: 'usuario/:pag/:ids/:pos',
    component: UsuarioComponent
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

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UsuarioTabComponent } from './usuario/usuario-tab.component';
import { UsuarioFormComponent } from './usuario/usuario-form.component';
import { UsuarioListarResolver } from './usuario/usuario-listar.resolver';
import { UsuarioCriarResolver } from './usuario/usuario-criar.resolver';
import { UsuarioVerResolver } from './usuario/usuario-ver.resolver';
import { PerfilComponent } from './perfil/perfil.component';

const routes: Routes = [
  {
    path: 'usuario/n',
    component: UsuarioFormComponent,
    resolve: {formulario: UsuarioCriarResolver},
    data: {}
  },
  {
    path: 'usuario/:id',
    component: UsuarioFormComponent,
    resolve: {formulario: UsuarioVerResolver},
    data: {}
  },
  {
    path: 'usuario',
    component: UsuarioTabComponent,
    resolve: {tabela: UsuarioListarResolver},
    data: {}
  },
  {
    path: 'perfil',
    component: PerfilComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [UsuarioListarResolver, UsuarioCriarResolver, UsuarioVerResolver]
})
export class PagRoutingModule { }

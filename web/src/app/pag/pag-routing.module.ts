import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PerfilComponent } from './perfil/perfil.component';
import { UsuarioListComponent } from './usuario/usuario-list.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuarioListResolver } from './usuario/usuario-list.resolver';
import { UsuarioResolver } from './usuario/usuario.resolver';

const routes: Routes = [
  {
    path: 'usuario',
    component: UsuarioListComponent,
    resolve: {dados: UsuarioListResolver}
  },
  {
    path: 'usuario/:pag',
    component: UsuarioListComponent,
    resolve: {dados: UsuarioListResolver}
  },
  {
    path: 'usuario/:pag/n',
    component: UsuarioComponent,
    resolve: {registro: UsuarioResolver}
  },
  {
    path: 'usuario/:pag/:ids',
    component: UsuarioComponent,
    resolve: {registro: UsuarioResolver}
  },
  {
    path: 'usuario/:pag/:ids/n',
    component: UsuarioComponent,
    resolve: {registro: UsuarioResolver}
  },
  {
    path: 'usuario/:pag/:ids/:pos',
    component: UsuarioComponent,
    resolve: {registro: UsuarioResolver}
  },
  {
    path: 'perfil',
    component: PerfilComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [UsuarioListResolver, UsuarioResolver]
})
export class PagRoutingModule { }

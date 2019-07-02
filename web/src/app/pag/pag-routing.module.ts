import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PerfilComponent } from './perfil/perfil.component';
import { UsuarioListComponent } from './usuario/usuario-list.component';

const routes: Routes = [
  {
    path: "usuario-list",
    component: UsuarioListComponent
  },
  {
    path: "perfil",
    component: PerfilComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagRoutingModule { }

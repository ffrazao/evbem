import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
import { UsuarioResCriarService } from './servico/usuario-res-criar.service';
import { UsuarioResVerService } from './servico/usuario-res-ver.service';
import { UsuarioTabComponent } from './usuario-tab/usuario-tab.component';
import { UsuarioResListarService } from './servico/usuario-res-listar.service';

const routes: Routes = [
  {
    path: '',
    component: UsuarioTabComponent,
    resolve: { tabela: UsuarioResListarService },
    data: {}
  },
  {
    path: 'n',
    component: UsuarioFormComponent,
    resolve: { formulario: UsuarioResCriarService },
    data: {}
  },
  {
    path: ':id',
    component: UsuarioFormComponent,
    resolve: { formulario: UsuarioResVerService },
    data: {}
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }

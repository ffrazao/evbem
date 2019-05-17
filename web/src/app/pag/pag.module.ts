import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagRoutingModule } from './pag-routing.module';
import { UsuarioComponent } from './usuario/usuario.component';
import { PerfilComponent } from './perfil/perfil.component';

@NgModule({
  declarations: [UsuarioComponent, PerfilComponent],
  imports: [
    CommonModule,
    PagRoutingModule
  ], 
  exports: [],
})
export class PagModule { }

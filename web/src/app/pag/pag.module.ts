import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagRoutingModule } from './pag-routing.module';
import { UsuarioComponent } from './usuario/usuario.component';
import { PerfilComponent } from './perfil/perfil.component';
import { UsuarioListComponent } from './usuario/usuario-list.component';
import { MatButtonModule, MatFormFieldModule, MatInputModule, MatPaginatorModule, MatProgressSpinnerModule, MatRippleModule, MatTableModule, MatCheckboxModule } from '@angular/material';

@NgModule({
  declarations: [UsuarioComponent, UsuarioListComponent, PerfilComponent],
  imports: [
    CommonModule,
    PagRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatRippleModule,
    MatTableModule,
    MatCheckboxModule,
  ], 
  exports: [],
})
export class PagModule { }

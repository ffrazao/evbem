import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatFormFieldModule, MatInputModule, MatPaginatorModule, MatProgressSpinnerModule, MatRippleModule, MatTableModule, MatCheckboxModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { PagRoutingModule } from './pag-routing.module';
import { UsuarioTabComponent } from './usuario/usuario-tab.component';
import { UsuarioFormComponent } from './usuario/usuario-form.component';
import { PerfilComponent } from './perfil/perfil.component';

@NgModule({
  declarations: [UsuarioTabComponent, UsuarioFormComponent, PerfilComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
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
  exports: []
})
export class PagModule { }

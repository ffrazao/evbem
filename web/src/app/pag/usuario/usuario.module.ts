import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatFormFieldModule, MatInputModule, MatPaginatorModule, MatProgressSpinnerModule, MatRippleModule, MatTableModule, MatCheckboxModule, MatDialogModule } from '@angular/material';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioTabComponent } from './usuario-tab/usuario-tab.component';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
import { UsuarioFiltroComponent } from './usuario-filtro/usuario-filtro.component';
import { UsuarioResCriarService } from './servico/usuario-res-criar.service';
import { UsuarioResVerService } from './servico/usuario-res-ver.service';
import { UsuarioResListarService } from './servico/usuario-res-listar.service';

@NgModule({
  declarations: [UsuarioTabComponent, UsuarioFormComponent, UsuarioFiltroComponent],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatRippleModule,
    MatTableModule,
    MatCheckboxModule,
    MatDialogModule,
  ],
  exports: [
    MatDialogModule
  ],
  providers: [UsuarioResCriarService, UsuarioResVerService, UsuarioResListarService]
})
export class UsuarioModule { }

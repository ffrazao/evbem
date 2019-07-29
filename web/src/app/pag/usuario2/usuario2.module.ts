import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Usuario2RoutingModule } from './usuario2-routing.module';
import { UsuarioComponent } from './usuario/usuario.component';
import { TabComponent } from './usuario/comp/tab/tab.component';
import { FiltroComponent } from './usuario/comp/filtro/filtro.component';
import { FormComponent } from './usuario/comp/form/form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComumModule } from 'src/app/comum/comum.module';

@NgModule({
  declarations: [UsuarioComponent, TabComponent, FiltroComponent, FormComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ComumModule,
    Usuario2RoutingModule
  ]
})
export class Usuario2Module { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { UsuarioComponent } from './usuario/usuario.component';

@NgModule({
  declarations: [LoginComponent, UsuarioComponent],
  imports: [
    CommonModule
  ]
})
export class PagModule { }

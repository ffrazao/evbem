import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pag/login/login.component';
import { UsuarioComponent } from './pag/usuario/usuario.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "usuario", component: UsuarioComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

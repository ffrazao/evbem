import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login/login.service';
import { UsuarioLocal } from 'src/app/login/usuario-local';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-menu-usuario',
  templateUrl: 'menu-usuario.component.html',
  styleUrls: ['menu-usuario.component.scss']
})
export class MenuUsuarioComponent {

  private usuarioLogado$: Observable<UsuarioLocal> = null;

  constructor(
    private service: LoginService,

  ) {
    this.usuarioLogado$ = this.service.usuarioLogado;
  }

  public logout() {
    this.service.logout();
  }

}

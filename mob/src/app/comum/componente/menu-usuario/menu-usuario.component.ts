import { Component } from '@angular/core';
import { Observable } from 'rxjs';

import { LoginService } from '../login/login.service';
import { UsuarioLocal } from '../login/usuario-local';

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

}

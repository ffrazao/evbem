import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Usuario } from 'src/app/entidade/usuario';
import { UsuarioService } from './usuario.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioResListarService implements Resolve<Observable<Usuario[]>> {

  constructor(
    private _service: UsuarioService
  ) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Usuario[]> {
    if (route.data.config && route.data.config.fonteDados) {
      return route.data.config.fonteDados.data;
    } else {
      return this._service.listar();
    }
  }

}
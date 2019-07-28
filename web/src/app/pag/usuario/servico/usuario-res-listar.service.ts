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
    console.log('resolvendo tab');
    if (route.data && route.data.config) {
      if (route.data.config.fonteDados) {
        return route.data.config.fonteDados.data;
      } else if (route.data.config.filtro) {
        return this._service.listar(route.data.config.filtro.value);
      }
    }
    return this._service.listar();
  }

}
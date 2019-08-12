import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Produto } from 'src/app/entidade/produto';
import { ProdutoService } from './produto.service';

@Injectable({
  providedIn: 'root'
})
export class ResListarService implements Resolve<Observable<Produto[]>> {

  constructor(
    private _service: ProdutoService
  ) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Produto[]> {
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
import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Produto } from 'src/app/entidade/produto';
import { ProdutoService } from './produto.service';

@Injectable({
  providedIn: 'root'
})
export class ResCriarService implements Resolve<Observable<Produto>> {
    
  constructor(
      private _service: ProdutoService
  ) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Produto> {
      return this._service.criar();
  }

}

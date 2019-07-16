import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Usuario } from 'src/app/entidade/usuario';
import { UsuarioService } from './usuario.service';

@Injectable()
export class UsuarioVerResolver implements Resolve<Observable<Usuario>> {
    
    constructor(
        private _service: UsuarioService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Usuario> {
        return this._service.ver(route.params.id);
    }

}
import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Usuario } from 'src/app/entidade/usuario';
import { UsuarioService } from './usuario.service';
import { Observable } from 'rxjs';

@Injectable()
export class UsuarioResolver implements Resolve<Observable<Usuario>> {
    
    constructor(
        private _service: UsuarioService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Usuario> {
        console.log('vai ver por id');
        return this._service.ver(route.params.pag);
    }

}
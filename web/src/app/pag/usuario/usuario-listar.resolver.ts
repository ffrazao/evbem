import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Usuario } from 'src/app/entidade/usuario';
import { UsuarioService } from './usuario.service';
import { Observable } from 'rxjs';

@Injectable()
export class UsuarioListarResolver implements Resolve<Observable<Usuario[]>> {
    
    constructor(
        private _service: UsuarioService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Usuario[]> {
        return this._service.listar();
    }

}
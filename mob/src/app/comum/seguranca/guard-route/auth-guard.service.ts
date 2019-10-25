import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router } from '@angular/router';
import { LoginService } from 'src/app/login/login.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(
        private service: LoginService,
        private router: Router
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let usuarioLocal = null;
        (async () => {
            usuarioLocal = await this.service.usuarioLocal();
        })();
        console.log('usuarioLocal && usuarioLocal.access_token', usuarioLocal && usuarioLocal.access_token);
        let usuarioLocalLogado = usuarioLocal && usuarioLocal.access_token;
        if (!usuarioLocalLogado) {
            this.router.navigate(['/', 'login']);
        }
        return usuarioLocalLogado;
    }

}

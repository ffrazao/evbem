import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanLoad, Route, UrlSegment, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../seguranca/auth-service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate, CanLoad {

    constructor(
        private service: AuthService,
        private router: Router,
    ) {
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        return this.podeAcessar();
    }

    canLoad(
        route: Route,
        segments: UrlSegment[]): boolean | Observable<boolean> | Promise<boolean> {
        return this.podeAcessar();
    }

    private podeAcessar(): Promise<boolean> {
        return new Promise<boolean>((resolve, reject) => {
            this.service.estaAutenticado()
                .then((r) => {
                    resolve(r);
                    if (!r) {
                        this.router.navigate(['/login']);
                    }
                })
                .catch((e) => {
                    reject(`Erro ao tentar acessar! [${JSON.stringify(e)}]`);
                    this.router.navigate(['/login']);
                });
        });
    }

}
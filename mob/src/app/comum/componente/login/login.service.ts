import { Injectable, Type } from '@angular/core';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { File } from '@ionic-native/file/ngx';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

import { ApiService } from '../../servico/externo/api.service';
import { environment } from '../../../../environments/environment';
import { StaticInjectorService } from '../../ferramenta/static-injector-service';
import { Login } from './login';
import { UsuarioLocal } from './usuario-local';

const func = '/oauth';
const tokenAutenticacao = 'tokenAutenticacao';

@Injectable({ providedIn: 'root' })
export class LoginService extends ApiService {

    private router: Router;
    private sanitizer: DomSanitizer;
    private file: File;

    private usuarioLogadoP = new BehaviorSubject<UsuarioLocal>(null);

    constructor(protected http: HttpClient) {
        super(http);
        this.router = StaticInjectorService.injector.get<Router>(Router as Type<Router>);
        this.sanitizer = StaticInjectorService.injector.get<DomSanitizer>(DomSanitizer as Type<DomSanitizer>);
        this.file = StaticInjectorService.injector.get<File>(File as Type<File>);
        this.atualizaENotifica();
    }

    public login(login: Login) {
        const credentials = `Basic ${btoa(`${environment.CLIENT_ID}:${environment.CLIENT_SECRET}`)}`;
        return this.http.post(
            `${environment.autorizadorUrl}${func}/token`, {},
            {
                observe: 'response',
                headers: new HttpHeaders({
                    Authorization: credentials
                }),
                params: {
                    username: login.usuario,
                    password: login.senha,
                    grant_type: 'password',
                }
            }
        ).pipe(
            tap((res) => {
                const usuarioLocal = res.body as UsuarioLocal;
                if (!usuarioLocal) {
                    throw new Error('Problemas ao autenticar o usuário!');
                }
                localStorage.setItem(tokenAutenticacao, JSON.stringify(usuarioLocal));
                this.atualizaENotifica();
            }));
    }

    public logout() {
        localStorage.removeItem(tokenAutenticacao);
        this.atualizaENotifica();
        this.router.navigate(['/']);

        this.http.post(`${environment.autorizadorUrl}/logout.do`, null).subscribe(() => {
            localStorage.removeItem(tokenAutenticacao);
            this.atualizaENotifica();
            this.router.navigate(['/']);
        }, (e) => {
            console.log(e);
        });
    }

    public get temToken() {
        return localStorage.getItem(tokenAutenticacao) != null;
    }

    public get token(): UsuarioLocal {
        return JSON.parse(localStorage.getItem(tokenAutenticacao)) as UsuarioLocal;
    }

    public get usuarioLogado() {
        return this.usuarioLogadoP.asObservable();
    }

    private async atualizaENotifica() {
        const usuarioLocal = JSON.parse(localStorage.getItem(tokenAutenticacao)) as UsuarioLocal;
        if (usuarioLocal) {
            if (usuarioLocal.foto && usuarioLocal.foto.length) {
                usuarioLocal.fotoSafe = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpeg;base64, ' + usuarioLocal.foto);
            }
        }
        this.usuarioLogadoP.next(usuarioLocal);
    }

}

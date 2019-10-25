import { Injectable, Type } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';

import { ApiService } from '../comum/servico/externo/api.service';
import { Login } from './login';
import { environment } from 'src/environments/environment';
import { Storage } from '@ionic/storage';

import { StaticInjectorService } from '../comum/ferramenta/static-injector-service';
import { UsuarioLocal } from './usuario-local';

const func = "/oauth";
const tokenAutenticacao = "tokenAutenticacao";

@Injectable({ providedIn: 'root' })
export class LoginService extends ApiService {

    private storage: Storage;

    constructor(protected http: HttpClient) {
        super(http);
        this.storage = StaticInjectorService.injector.get<Storage>(Storage as Type<Storage>);
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
        ).pipe(tap(resposta => {
            // captar o token de autenticação e armazenar
            console.log('resposta', resposta);
            const resp: UsuarioLocal = resposta.body as UsuarioLocal;
            if (!resp) {
                throw new Error('Problemas ao autenticar o usuário!');
            }
            this.storage.set(tokenAutenticacao, JSON.stringify(resp));
        }));
    }

    public logout(): Promise<boolean> {
        return new Promise((resolve, reject) => {
            return this.http.post(`${environment.autorizadorUrl}/logout`, null).subscribe(() => {
                this.storage.remove(tokenAutenticacao);
                resolve(true);
            }, (e) => {
                reject(e);
            });
        });
    }

    public async usuarioLocal(): Promise<UsuarioLocal> {
        // return new Promise<UsuarioLocal>(async (resolve, reject) => {
            let result: UsuarioLocal = null;
            result = JSON.parse(await this.storage.get(tokenAutenticacao)) as UsuarioLocal;
            // resolve(result);
        // });
        return result;
    }

}

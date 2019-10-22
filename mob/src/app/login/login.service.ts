import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';

import { ApiService } from '../comum/servico/externo/api.service';
import { Login } from './login';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class LoginService extends ApiService {

    constructor(protected http: HttpClient) {
        super(http);
    }

    public login(login: Login) {
        const credentials = `Basic ${btoa(`${environment.CLIENT_ID}:${environment.CLIENT_SECRET}`)}`;
        console.log('credentials', credentials);

        return this.http.post(
            `${environment.autorizadorUrl}/oauth/token`, {},
            {
                observe: 'response',
                headers: new HttpHeaders({
                    'Content-Type': 'application/json; charset=utf-8',
                    Authorization: credentials
                }),
                params: {
                    grant_type: 'password',
                    username: login.usuario,
                    password: login.senha,
                }
            }
        ).pipe(tap(resposta => {
            // captar o token de autenticação e armazenar
            console.log('resposta', resposta);
            const resp: any = resposta.body; // as RespostaRestAPI;
            const token = resp.access_token;
            if (!token) {
                throw new Error('Problemas ao autenticar o usuário!');
            }
            /*this._tokenService.setToken(token);
            this._http.get(environment.REST_API_URL + '/usuario', {
              observe: 'response',
              headers: new HttpHeaders({
                'Content-Type' : 'application/json; charset=utf-8',
                'Authorization': `Bearer ${token}`
              })
            })
              .subscribe((resposta) => {
                this._usuarioService.login((resposta.body as Usuario));
                super.getRouter().navigate(['s', 'home']);
              }, err => alert('Erro get usuario ' + JSON.stringify(err)));
          */
        }));
    }

    public logout() {
        return this.http.post(`${this.url}/logout`, null);
    }

}

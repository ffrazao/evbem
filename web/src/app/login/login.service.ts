import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { ConsultaCepService } from '../comum/servico/consulta-cep.service';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';
import { Token } from '../entidade/token';
import { Router } from '@angular/router';

export const KEY_TOKEN = 'token';
export const URL_OAUTH_TOKEN = '/oauth/token';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private _http: HttpClient,
    private _router: Router) {
  }

  public login(username, password) {
    const url = `${environment.AUTORIZADOR_SERVER_URL}${URL_OAUTH_TOKEN}`;
    const data = `grant_type=password&username=${username}&password=${password}`;
    const options = {
      'headers': new HttpHeaders({
        "Content-Type": "application/x-www-form-urlencoded",
        "Authorization": `Basic ${btoa('evbem_web:evbem_web')}`,
      }),
      'observe': 'response' as 'body'
    };

    return this._http.post(url, data, options).pipe(
      tap(
        data => {
          window.localStorage.setItem(KEY_TOKEN, JSON.stringify((data as HttpResponse<Token>).body));
        }
      )
    );
  }

  public token(): Token {
    return JSON.parse(window.localStorage.getItem(KEY_TOKEN)) as Token;
  }

  public estaLogado(): boolean {
    return !(!this.token());
  }

  public logout() {
    window.localStorage.removeItem(KEY_TOKEN);
    this._router.navigate(['/home']);
  }

}

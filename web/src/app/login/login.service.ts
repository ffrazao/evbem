import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { ConsultaCepService } from '../comum/servico/consulta-cep.service';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';
import { Token } from '../entidade/token';

const KEY_TOKEN = 'token';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private _http: HttpClient) {
  }

  public login(username, password) {
    const url = environment.AUTORIZADOR_SERVER_URL + '/oauth/token';
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

}

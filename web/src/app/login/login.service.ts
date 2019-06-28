import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';

import { environment } from 'src/environments/environment';
import { Token } from '../entidade/token';
import { Login } from '../entidade/login';
import { LoginComponent } from './login.component';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../comum/servico/token.service';

export const URL_OAUTH_TOKEN = '/oauth/token';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private _http: HttpClient,
    private _dialog: MatDialog,
    private _router: Router,
    private _toastr: ToastrService,
    private _tokenService: TokenService
    ) {
  }

  public exibeLogin(data: Login): void {
      const dialogRef = this._dialog.open(LoginComponent, {
          width: '60%',
          data: data
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.login(result).subscribe((res) => {
            this._router.navigate(['/home']);
            this._toastr.success('Executado com sucesso!', 'Login');  
          });
        }
      });
  }

  public login(login: Login) {
    const url = `${environment.AUTORIZADOR_SERVER_URL}${URL_OAUTH_TOKEN}`;
    const data = `grant_type=password&username=${login.usuario}&password=${login.senha}`;
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
          this._tokenService.set(JSON.stringify((data as HttpResponse<Token>).body));
        }
      )
    );
  }

  public token(): Token {
    return JSON.parse(this._tokenService.get()) as Token;
  }

  public estaLogado(): boolean {
    return this._tokenService.temToken();
  }

  public logout() {
    const url = `${environment.AUTORIZADOR_SERVER_URL}/logout`;

    return this._http.get(url).pipe(
      tap(
        data => {
          this._tokenService.clear();
        }
      )
    );
  }

}

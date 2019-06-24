import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConsultaCepService } from '../comum/servico/consulta-cep.service';
import { environment } from 'src/environments/environment';
import { headersToString } from 'selenium-webdriver/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient,
    private _consultaCepService: ConsultaCepService) { }

  public login(username, password) {
    const client = btoa('evbem_web:senhaevbem');

    return this._http.post(environment.AUTORIZADOR_SERVER_URL + '/oauth/token',
      {
        'grant_type': 'password',
        'username': username,
        'password': password,
      },
      {
        observe: 'response',
        //withCredentials: true,
        //params: {'grant_type': 'password'},
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': `Basic ${client}`
        })
      }).pipe(
        tap( // Log the result or error
          data => console.log('Data', data),
          error => console.error('Erro', error)
        )
      );
  }

}

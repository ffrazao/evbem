import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConsultaCepService } from '../comum/servico/consulta-cep.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient,
    private _consultaCepService: ConsultaCepService) { }

  public login(username, password) {
    return this._http.post(environment.AUTORIZADOR_SERVER_URL + '/oauth/authorize', {username, password});
  }

}

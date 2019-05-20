import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConsultaCepService } from '../comum/servico/consulta-cep.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient,
    private _consultaCepService: ConsultaCepService) { }

  public login() {
    //return this._http.get("http://viacep.com.br/ws/01001000/json/");
    return this._consultaCepService.buscarPorCep('72152-318');
  }

}

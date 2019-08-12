import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Produto } from 'src/app/entidade/produto';

const FUNCIONALIDADE = 'produto';

const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(
    private _http: HttpClient
  ) {
  }

  private getParametro(p: object = null): HttpParams {
    let result: HttpParams = null;
    if (p) {
      result = new HttpParams().set("filtro", encodeURIComponent(JSON.stringify(p)));
    }
    return result;
  }

  alterar(registroList: Produto | Produto[]) {
    return this._http.post(`${environment.API_URL}/${FUNCIONALIDADE}`, Array.isArray(registroList) ? registroList : [registroList]);
  }

  criar(modelo: object = null) {
    return this._http.get<Produto>(`${environment.API_URL}/${FUNCIONALIDADE}/criar`, { headers, params: this.getParametro(modelo) });
  }

  excluir(idList: number | number[]) {
    return this._http.delete(`${environment.API_URL}/${FUNCIONALIDADE}/${Array.isArray(idList) ? idList : [idList]}`);
  }

  inserir(registroList: Produto | Produto[]) {
    return this._http.put(`${environment.API_URL}/${FUNCIONALIDADE}`, Array.isArray(registroList) ? registroList : [registroList]);
  }

  listar(filtro: object = null) {
    return this._http.get<Produto[]>(`${environment.API_URL}/${FUNCIONALIDADE}`, { headers, params: this.getParametro(filtro) });
  }

  salvar(registroList: Produto | Produto[]) {
    return this._http.post(`${environment.API_URL}/${FUNCIONALIDADE}/salvar`, Array.isArray(registroList) ? registroList : [registroList]);
  }

  ver(idList: number | number[]) {
    return this._http.get<Produto>(`${environment.API_URL}/${FUNCIONALIDADE}/${Array.isArray(idList) ? idList : [idList]}`);
  }

}
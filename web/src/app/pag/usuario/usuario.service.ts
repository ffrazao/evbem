import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Usuario } from 'src/app/entidade/usuario';

const FUNCIONALIDADE = 'usuario';

@Injectable({providedIn: 'root'}) 
export class UsuarioService {

    constructor(
        private _http: HttpClient
    ) {
    }

    listar() {
        return this._http.get<Usuario[]>(`${environment.API_URL}/${FUNCIONALIDADE}`);
    }

    ver(id: number) {
        return this._http.get<Usuario>(`${environment.API_URL}/${FUNCIONALIDADE}/${id}`);
    }

    inserir(usuario: Usuario) {
        return this._http.put(`${environment.API_URL}/${FUNCIONALIDADE}`, usuario);
    }

    alterar(usuario: Usuario) {
        return this._http.post(`${environment.API_URL}/${FUNCIONALIDADE}`, usuario);
    }

    salvar(usuario: Usuario) {
        return usuario.id ? this.inserir(usuario) : this.alterar(usuario);
    }

    excluir(id: number) {
        return this._http.delete(`${environment.API_URL}/${FUNCIONALIDADE}/${id}`);
    }
    
}
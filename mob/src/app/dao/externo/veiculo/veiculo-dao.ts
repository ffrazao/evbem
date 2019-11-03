import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { Crud } from '../../crud';
import { Veiculo } from '../../../entidade/veiculo/veiculo';

const funcionalidade = 'veiculo';

@Injectable({ providedIn: 'root' })
export class VeiculoDao implements Crud<Veiculo, any, Veiculo> {

    constructor(
        private http: HttpClient
    ) {
    }

    iniciar(modelo?: Veiculo): Observable<Veiculo> {

        const query = `produto.marca.nome=${modelo.produto.marca.nome}&id=45`;
        return this.http.get<Veiculo>(`${environment.apiUrl}/${funcionalidade}/iniciar?${query}`,
            // { params:  }
        );
    }

    criar(entidades: Veiculo[]): Observable<number[]> {
        throw new Error('Method not implemented.');
    }
    restaurar(ids: number[]): Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }
    alterar(entidades: { id: number; entidade: Veiculo; }[]): Observable<{ id: number; entidade: Veiculo; }[]> {
        throw new Error('Method not implemented.');
    }
    excluir(ids: number[]): Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error('Method not implemented.');
    }
    listar(filtro: any): Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }
    salvar(entidades: Veiculo[]): Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }

}

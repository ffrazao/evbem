import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { Crud } from '../../crud';
import { Pessoa } from '../../../entidade/principal/pessoa';
import { PessoaFiltroDto } from 'src/app/transporte/principal/pessoa.filtro.dto';

const funcionalidade = 'pessoa';

@Injectable({ providedIn: 'root' })
export class PessoaDao implements Crud<Pessoa, PessoaFiltroDto, Pessoa> {

    constructor(
        private http: HttpClient
    ) {
    }

    iniciar(modelo?: Pessoa): Observable<Pessoa> {
        const query = `?`;
        return this.http.get<Pessoa>(`${environment.apiUrl}/${funcionalidade}/iniciar${query}`,
            // { params:  }
        );
    }

    criar(entidades: Pessoa[]): Observable<number[]> {
        throw new Error('Method not implemented.');
    }

    restaurar(ids: number[]): Observable<Pessoa[]> {
        throw new Error('Method not implemented.');
    }

    alterar(entidades: { id: number; entidade: Pessoa; }[]): Observable<{ id: number; entidade: Pessoa; }[]> {
        throw new Error('Method not implemented.');
    }

    excluir(ids: number[]): Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error('Method not implemented.');
    }

    listar(filtro: PessoaFiltroDto): Observable<Pessoa[]> {
        const query = `?pesq=${filtro.pesq}`;
        return this.http.get<Pessoa[]>(`${environment.apiUrl}/${funcionalidade}${query}`,
            // { params:  }
        );
    }

    salvar(entidades: Pessoa[]): Observable<Pessoa[]> {
        throw new Error('Method not implemented.');
    }

}

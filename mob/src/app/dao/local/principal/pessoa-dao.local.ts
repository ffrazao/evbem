import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { SqliteService } from '../../../comum/servico/local/sqlite.service';
import { Crud } from '../../crud';

import { Pessoa } from 'src/app/entidade/principal/pessoa';
import { PessoaFiltroDto } from 'src/app/transporte/principal/pessoa.filtro.dto';

@Injectable({ providedIn: 'root' })
export class PessoaDaoLocal implements Crud<Pessoa, PessoaFiltroDto, Pessoa> {

    constructor(
        private sqlite: SqliteService
    ) {
    }

    iniciar(modelo?: Map<string, string>): Observable<Pessoa> {
        throw new Error('Method not implemented.');
    }

    criar(entidades: Pessoa[]): Observable<Pessoa[]> {
        throw new Error('Method not implemented.');
    }
    restaurar(ids: number[]): Observable<Pessoa[]> {
        throw new Error('Method not implemented.');
    }
    alterar(entidades: { id: number, entidade: Pessoa}[]): Observable<{ id: number, entidade: Pessoa}[]> {
        throw new Error('Method not implemented.');
    }
    excluir(ids: number[]): Observable<{ id: number, resultado: boolean }[]> {
        throw new Error('Method not implemented.');
    }
    listar(filtro: PessoaFiltroDto): Observable<any[]> {
        throw new Error('Method not implemented.');
    }
    salvar(entidades: Pessoa[]): Observable<Pessoa[]> {
        throw new Error('Method not implemented.');
    }

}

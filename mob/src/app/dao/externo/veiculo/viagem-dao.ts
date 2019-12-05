import { Injectable } from '@angular/core';

import { SqliteService } from '../../../comum/servico/local/sqlite.service';
import { Crud } from '../../crud';
import { Viagem } from '../../../entidade/veiculo/viagem';

@Injectable({ providedIn: 'root' })
export class ViagemDao implements Crud<Viagem, any, Viagem> {

    constructor(
        private sqlite: SqliteService
    ) {
    }

    iniciar(modelo?: Map<string, string>): import('rxjs').Observable<Viagem> {
        throw new Error('Method not implemented.');
    }
    criar(entidades: Viagem[]): import('rxjs').Observable<Viagem[]> {
        throw new Error('Method not implemented.');
    }
    restaurar(ids: number[]): import('rxjs').Observable<Viagem[]> {
        throw new Error('Method not implemented.');
    }
    alterar(entidades: { id: number; entidade: Viagem; }[]): import('rxjs').Observable<{ id: number; entidade: Viagem; }[]> {
        throw new Error('Method not implemented.');
    }
    excluir(ids: number[]): import('rxjs').Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error('Method not implemented.');
    }
    listar(filtro: any): import('rxjs').Observable<Viagem[]> {
        throw new Error('Method not implemented.');
    }
    salvar(entidades: Viagem[]): import('rxjs').Observable<Viagem[]> {
        throw new Error('Method not implemented.');
    }

}

import { Injectable } from '@angular/core';

import { SqliteService } from '../../../comum/servico/local/sqlite.service';
import { Crud } from '../../crud';
import { Viagem } from '../../../entidade/veiculo/viagem';

@Injectable({ providedIn: 'root' })
export class ViagemDaoLocal implements Crud<Viagem, any, any> {

    constructor(
        private sqlite: SqliteService
    ) {
    }

    iniciar(modelo?: Viagem): import('rxjs').Observable<Viagem> {
        throw new Error('Method not implemented.');
    }

    criar(entidades: Viagem[]): import('rxjs').Observable<number[]> {
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
    listar(filtro: any): import('rxjs').Observable<any[]> {
        throw new Error('Method not implemented.');
    }
    salvar(entidades: Viagem[]): import('rxjs').Observable<Viagem[]> {
        throw new Error('Method not implemented.');
    }

}

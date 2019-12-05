import { Injectable } from '@angular/core';

import { SqliteService } from '../../../comum/servico/local/sqlite.service';
import { Crud } from '../../crud';
import { Veiculo } from '../../../entidade/veiculo/veiculo';

@Injectable({ providedIn: 'root' })
export class VeiculoDaoLocal implements Crud<Veiculo, any, any> {

    constructor(
        private sqlite: SqliteService
    ) {
    }

    iniciar(modelo?: Map<string, string>): import('rxjs').Observable<Veiculo> {
        throw new Error('Method not implemented.');
    }

    criar(entidades: Veiculo[]): import('rxjs').Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }
    restaurar(ids: number[]): import('rxjs').Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }
    alterar(entidades: { id: number; entidade: Veiculo; }[]): import('rxjs').Observable<{ id: number; entidade: Veiculo; }[]> {
        throw new Error('Method not implemented.');
    }
    excluir(ids: number[]): import('rxjs').Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error('Method not implemented.');
    }
    listar(filtro: any): import('rxjs').Observable<any[]> {
        throw new Error('Method not implemented.');
    }
    salvar(entidades: Veiculo[]): import('rxjs').Observable<Veiculo[]> {
        throw new Error('Method not implemented.');
    }

}

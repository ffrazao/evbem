import { Observable } from 'rxjs';

import { Crud } from '../crud';
import { FiltroDto } from 'src/app/comum/transporte/filtro';

export class Sincrono<LDao, EDao, E, F extends FiltroDto, R> implements Crud<E, F, R> {

    private _localDao: LDao;
    private _externoDao: EDao;

    constructor(local: LDao, externo: EDao) {
        this._localDao = local;
        this._externoDao = externo;
    }

    alterar(entidades: { id: number; entidade: E; }[]): Observable<{ id: number; entidade: E; }[]> {
        throw new Error("Method not implemented.");
    }
    
    criar(entidades: E[]): Observable<E[]> {
        throw new Error("Method not implemented.");
    }

    excluir(ids: number[]): Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error("Method not implemented.");
    }

    iniciar(modelo?: Map<string, string>): Observable<E> {
        throw new Error("Method not implemented.");
    }

    listar(filtro?: F): Observable<R[]> {
        throw new Error("Method not implemented.");
    }

    restaurar(ids: number[]): Observable<E[]> {
        throw new Error("Method not implemented.");
    }

    salvar(entidades: E[]): Observable<E[]> {
        throw new Error("Method not implemented.");
    }

}

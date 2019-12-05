import { Crud } from '../crud';
import { FiltroDto } from 'src/app/comum/transporte/filtro';

export class Sincrono<LDao, EDao, E, F extends FiltroDto, R> implements Crud<E, F, R> {

    private _localDao: LDao;
    private _externoDao: EDao;

    constructor(local: LDao, externo: EDao) {
        this._localDao = local;
        this._externoDao = externo;
    }

    alterar(entidades: { id: number; entidade: E; }[]): import("rxjs").Observable<{ id: number; entidade: E; }[]> {
        throw new Error("Method not implemented.");
    }
    criar(entidades: E[]): import("rxjs").Observable<E[]> {
        throw new Error("Method not implemented.");
    }
    excluir(ids: number[]): import("rxjs").Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error("Method not implemented.");
    }
    iniciar(modelo?: Map<string, string>): import("rxjs").Observable<E> {
        throw new Error("Method not implemented.");
    }
    listar(filtro?: F): import("rxjs").Observable<R[]> {
        throw new Error("Method not implemented.");
    }
    restaurar(ids: number[]): import("rxjs").Observable<E[]> {
        throw new Error("Method not implemented.");
    }
    salvar(entidades: E[]): import("rxjs").Observable<E[]> {
        throw new Error("Method not implemented.");
    }

}

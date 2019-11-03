import { Observable } from 'rxjs';
import { FiltroDto } from '../comum/transporte/filtro';

export interface Crud<E, F extends FiltroDto, R> {

    iniciar(modelo?: E): Observable<E>;

    criar(entidades: E[]): Observable<number[]>;

    restaurar(ids: number[]): Observable<E[]>;

    alterar(entidades: {id: number, entidade: E}[]): Observable<{id: number, entidade: E}[]>;

    excluir(ids: number[]): Observable<{id: number, resultado: boolean}[]>;

    listar(filtro: F): Observable<R[]>;

    salvar(entidades: E[]): Observable<E[]>;

}

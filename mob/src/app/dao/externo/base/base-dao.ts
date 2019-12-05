import { Injectable, Type } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { Crud } from '../../crud';
import { FiltroDto } from 'src/app/comum/transporte/filtro';
import { StaticInjectorService } from 'src/app/comum/ferramenta/static-injector-service';

@Injectable({ providedIn: 'root' })
export class BaseDaoExterno<E, F extends FiltroDto, R> implements Crud<E, F, R> {

    private _funcionalidade: string;

    private _http: HttpClient

    constructor(
        funcionalidade: string
    ) {
        this._funcionalidade = funcionalidade;
        this._http = StaticInjectorService.injector.get<HttpClient>(HttpClient as Type<HttpClient>);
    }

    get funcionalidade() {
        return this._funcionalidade;
    }

    iniciar(modelo?: Map<string, string>): Observable<E> {
        let query = '';
        modelo && modelo.forEach((v, k) => query += `${query === '' ? '?' : '&'}${k}=${v}`);
        return this._http.get<E>(`${environment.apiUrl}/${this.funcionalidade}/iniciar${encodeURI(query)}`);
    }

    criar(entidades: E[]): Observable<E[]> {
        throw new Error('Method not implemented.');
    }

    restaurar(ids: number[]): Observable<E[]> {
        throw new Error('Method not implementado.');
    }

    alterar(entidades: { id: number; entidade: E; }[]): Observable<{ id: number; entidade: E; }[]> {
        throw new Error('Method not implemented.');
    }

    excluir(ids: number[]): Observable<{ id: number; resultado: boolean; }[]> {
        throw new Error('Method not implemented.');
    }

    listar(filtro: F): Observable<R[]> {
        const query = `?pesq=${filtro['pesq']}`;
        return this._http.get<R[]>(`${environment.apiUrl}/${this.funcionalidade}${query}`,
            // { params:  }
        );
    }

    salvar(entidades: E[]): Observable<E[]> {
        throw new Error('Method not implemented.');
    }

}
